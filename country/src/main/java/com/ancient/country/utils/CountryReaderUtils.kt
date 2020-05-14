package com.ancient.country.utils

import android.app.Application
import android.content.Context
import android.util.Log
import com.ancient.country.model.CountryModel
import com.ancient.country.model.CurrencyModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import org.json.JSONObject

/**
 * Created by ancientinc on 2019-07-06.
 **/
object CountryReaderUtils {

    private const val COUNTRIES_FILE_NAME: String = "countries.json"
    private const val COUNTRIES_DIAL_FILE_NAME: String = "countries_dial.json"
    private const val COUNTRIES_CURRENCY_FILE_NAME: String = "country_currency_code.json"
    private const val COUNTRIES_CURRENCY_SYMBOLS_FILE_NAME: String = "countries_currency_symbols.json"

    private val countryList: MutableList<CountryModel> = mutableListOf()

    private fun readCountryList(aContext: Context, aAssetFileName: String?): MutableList<CountryModel>? {

        if (aAssetFileName.isNullOrBlank()) {
            return null
        }

        val categoryType = object : TypeToken<List<CountryModel>>() {}.type
        var jsonReader: JsonReader? = null
        try {
            val inputStream = aContext.assets.open(aAssetFileName)
            jsonReader = JsonReader(inputStream.reader())
            val list: MutableList<CountryModel> = Gson().fromJson(jsonReader, categoryType)
            list.sortBy {
                it.countryName
            }
            return list
        } catch (aException: Exception) {
            Log.e("", "Error seeding database", aException)
        } finally {
            jsonReader?.close()
        }

        return null
    }

    fun getCountryCode(aContext: Context, aCountryCode: String?): CountryModel? {
        return aCountryCode?.let {
            if (countryList.isEmpty()) {
                readCountryList(aContext, "countries.json")?.let {
                    countryList.addAll(it)
                }
            }

            readCountryList(aContext, "countries.json")

            countryList.find {
                it.countryCode == aCountryCode
            }
        }
    }

    fun mergeCountryCurrencies(aContext: Context) {

        var countryList: MutableList<CountryModel>? = readCountryList(aContext, COUNTRIES_FILE_NAME)

        val currencySymbolsList = readCurrencyList(aContext, COUNTRIES_CURRENCY_SYMBOLS_FILE_NAME)

        if (countryList?.isNotEmpty() == true && currencySymbolsList?.isNotEmpty() == true) {

            countryList.forEach { aCountry ->
                currencySymbolsList.find { aCountry.currencyCode == it.code }?.let {
                    aCountry.currency = it
                }
            }

            countryList = countryList.filter { it.currencyCode?.isNotBlank() == true }.toMutableList()

            if (countryList.isNotEmpty()) {
                convertToJsonString(countryList)
            }
        }
    }

    private fun readCurrencyList(aContext: Context, aAssetFileName: String?): MutableList<CurrencyModel>? {

        if (aAssetFileName.isNullOrBlank()) {
            return null
        }

        val currencyList: MutableList<CurrencyModel> = arrayListOf()
        try {
            val jsonString = aContext.assets.open(aAssetFileName).bufferedReader().use {
                it.readText()
            }

            val jsonObject = JSONObject(jsonString)
            val keys = jsonObject.keys()

            if (keys.hasNext()) {
                do {
                    val keyValue = keys.next()
                    val jsonValue: JSONObject = jsonObject.get(keyValue) as JSONObject
                    val currency: CurrencyModel = Gson().fromJson(jsonValue.toString(), CurrencyModel::class.java)
                    currencyList.add(currency)
                } while (keys.hasNext())
            }

            return currencyList
        } catch (aException: Exception) {
            Log.e("", "Error seeding database", aException)
        }

        return null
    }

    private fun convertToJsonString(aCountryList: MutableList<CountryModel>) {
        val jsonString = Gson().toJson(aCountryList)
        println(jsonString)
    }

    fun readCountryList(aApplication: Application): MutableList<CountryModel>? {
        return readCountryList(aApplication, COUNTRIES_FILE_NAME)
    }
}
package com.ancient.country.utils

import android.app.Application
import android.content.Context
import android.util.Log
import com.ancient.country.model.CountryModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader

/**
 * Created by ancientinc on 2019-07-06.
 **/
object CountryReaderUtils {

    private const val COUNTRIES_FILE_NAME: String = "countries.json"

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

    fun mergeCountryDialCode(aContext: Context) {

        var countryList: MutableList<CountryModel>? = readCountryList(aContext, COUNTRIES_FILE_NAME)

        val countryDialCodeList = readCountryList(aContext, "countries_dial.json")

        if (countryList?.isNotEmpty() == true && countryDialCodeList?.isNotEmpty() == true) {

            countryList.forEach { aCountry ->
                countryDialCodeList.find { aCountry.countryCode == it.countryCode }?.let {
                    aCountry.dialCode = it.dialCode
                }
            }

            countryList = countryList.filter {
                it.dialCode?.isNotBlank() == true
            }.toMutableList()

            if (countryList.isNotEmpty()) {
                convertToJsonString(countryList)
            }
        }
    }

    private fun convertToJsonString(aCountryList: MutableList<CountryModel>) {
        val jsonString = Gson().toJson(aCountryList)
        println(jsonString)
    }

    fun readCountryList(aApplication: Application): MutableList<CountryModel>? {
        return readCountryList(aApplication, COUNTRIES_FILE_NAME)
    }
}
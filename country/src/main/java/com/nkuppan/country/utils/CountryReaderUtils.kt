package com.nkuppan.country.utils

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.nkuppan.country.model.CountryModel
import com.nkuppan.country.model.CurrencyModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

/**
 * Created by ancientinc on 2019-07-06.
 **/

private const val COUNTRIES_FILE_NAME: String = "countries.json"

private val countryList: MutableList<CountryModel> = mutableListOf()

suspend fun readCountryList(
    context: Context, assetFileName: String?
): MutableList<CountryModel>? = withContext(Dispatchers.IO) {

    if (assetFileName.isNullOrBlank()) {
        return@withContext null
    }

    val categoryType = object : TypeToken<List<CountryModel>>() {}.type
    var jsonReader: JsonReader? = null
    try {
        val inputStream = context.assets.open(assetFileName)
        jsonReader = JsonReader(inputStream.reader())
        val list: MutableList<CountryModel> = Gson().fromJson(jsonReader, categoryType)
        list.sortBy {
            it.countryName
        }
        return@withContext list
    } catch (aException: Exception) {
        Log.e("", "Error seeding database", aException)
    } finally {
        jsonReader?.close()
    }

    return@withContext null
}

suspend fun getCountryCode(
    context: Context, countryCode: String?
): CountryModel? = withContext(Dispatchers.IO) {
    return@withContext countryCode?.let {
        if (countryList.isEmpty()) {
            readCountryList(context, COUNTRIES_FILE_NAME)?.let {
                countryList.addAll(it)
            }
        }

        readCountryList(context, COUNTRIES_FILE_NAME)

        countryList.find {
            it.countryCode == countryCode
        }
    }
}

private suspend fun readCurrencyList(
    context: Context,
    assetFileName: String?
): MutableList<CurrencyModel>? = withContext(Dispatchers.IO) {

    if (assetFileName.isNullOrBlank()) {
        return@withContext null
    }

    val currencyList: MutableList<CurrencyModel> = arrayListOf()
    try {
        val jsonString = context.assets.open(assetFileName).bufferedReader().use {
            it.readText()
        }

        val jsonObject = JSONObject(jsonString)
        val keys = jsonObject.keys()

        if (keys.hasNext()) {
            do {
                val keyValue = keys.next()
                val jsonValue: JSONObject = jsonObject.get(keyValue) as JSONObject
                val currency: CurrencyModel =
                    Gson().fromJson(jsonValue.toString(), CurrencyModel::class.java)
                currencyList.add(currency)
            } while (keys.hasNext())
        }

        return@withContext currencyList
    } catch (exception: Exception) {
        Log.e("", "Error seeding database", exception)
    }

    return@withContext null
}

suspend fun readCountryList(application: Application): MutableList<CountryModel>? {
    return readCountryList(application, COUNTRIES_FILE_NAME)
}
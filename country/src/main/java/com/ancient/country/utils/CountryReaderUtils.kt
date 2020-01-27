package com.ancient.country.utils

import android.content.Context
import android.util.Log
import com.ancient.country.model.CountryModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.lang.Exception

/**
 * Created by ancientinc on 2019-07-06.
 **/
object CountryReaderUtils {

    private val countryList: MutableList<CountryModel> = mutableListOf()

    fun readCountryList(aContext: Context): MutableList<CountryModel>? {

        if (countryList.isNotEmpty()) {
            return countryList
        }

        val categoryType = object : TypeToken<List<CountryModel>>() {}.type
        var jsonReader: JsonReader? = null
        try {
            val inputStream = aContext.assets.open("countries.json")
            jsonReader = JsonReader(inputStream.reader())
            val list: MutableList<CountryModel> = Gson().fromJson(jsonReader, categoryType)
            list.sortBy {
                it.countryName
            }
            countryList.addAll(list)
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
                readCountryList(aContext)
            }

            countryList.find {
                it.countryCode == aCountryCode
            }
        }
    }
}
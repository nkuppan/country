package com.nkuppan.country.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.nkuppan.country.domain.repository.JsonConverter
import java.io.InputStreamReader

class GsonJsonConverter(private val gson: Gson) : JsonConverter {

    override fun <T> fromStringToObject(
        inputStreamReader: InputStreamReader,
        classValue: Class<T>
    ): T? {
        return kotlin.runCatching {
            return gson.fromJson(JsonReader(inputStreamReader), classValue)
        }.getOrNull()
    }

    override fun <T> fromStringToListOfObject(
        inputStreamReader: InputStreamReader
    ): List<T>? {
        return kotlin.runCatching {
            val type = object : TypeToken<ArrayList<T>>() {}.type
            return gson.fromJson(JsonReader(inputStreamReader), type)
        }.getOrNull()
    }
}
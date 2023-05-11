package com.nkuppan.country.data.repository

import com.google.gson.Gson
import com.nkuppan.country.domain.repository.JsonConverter
import java.lang.reflect.Type

class GsonJsonConverter(private val gson: Gson) : JsonConverter {

    override fun <T> fromStringToListOfObject(
        jsonString: String,
        type: Type
    ): T? {
        return kotlin.runCatching {
            return gson.fromJson(jsonString, type)
        }.getOrNull()
    }
}
package com.github.nkuppan.country.domain.repository

import java.lang.reflect.Type

interface JsonConverter {

    fun <T> fromStringToListOfObject(
        jsonString: String,
        type: Type
    ): T?
}
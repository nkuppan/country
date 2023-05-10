package com.nkuppan.country.domain.repository

import java.io.InputStreamReader
import java.lang.reflect.Type

interface JsonConverter {

    fun <T> fromStringToObject(
        inputStreamReader: InputStreamReader,
        classValue: Class<T>
    ): T?

    fun <T> fromStringToListOfObject(
        inputStreamReader: InputStreamReader,
        type: Type
    ): List<T>?
}
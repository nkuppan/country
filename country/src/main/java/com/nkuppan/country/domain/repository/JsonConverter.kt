package com.nkuppan.country.domain.repository

import java.io.InputStreamReader

interface JsonConverter {

    fun <T> fromStringToObject(
        inputStreamReader: InputStreamReader,
        classValue: Class<T>
    ): T?

    fun <T> fromStringToListOfObject(
        inputStreamReader: InputStreamReader
    ): List<T>?
}
package com.nkuppan.country.data.repository

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.nkuppan.country.data.utils.convertFileToString
import com.nkuppan.country.domain.model.Country
import com.nkuppan.country.domain.repository.CountryRepository
import com.nkuppan.country.domain.repository.JsonConverter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository Implementation. Which carries the information about how we are reading the countries
 * information from the json files.
 */
class CountryRepositoryImpl(
    private val context: Context,
    private val jsonConverter: JsonConverter,
    private val jsonFileName: String = "countries.json",
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : CountryRepository {

    override suspend fun readCountries(): List<Country> = withContext(dispatcher) {
        return@withContext context.convertFileToString(fileName = jsonFileName)
            ?.let { jsonString ->
                return@let jsonConverter.fromStringToListOfObject<List<Country>>(
                    jsonString,
                    object :TypeToken<List<Country>>() {}.type
                )
            } ?: emptyList()
    }
}
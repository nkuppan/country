package com.nkuppan.country.data.repository

import android.content.Context
import com.nkuppan.country.data.utils.openInputStreamReader
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
        return@withContext context.openInputStreamReader(fileName = jsonFileName)
            ?.let { streamReader ->
                return@let jsonConverter.fromStringToListOfObject<Country>(
                    streamReader
                )?.toMutableList()
            } ?: emptyList()
    }
}
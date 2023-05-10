package com.nkuppan.country.domain.repository

import com.nkuppan.country.domain.model.Country

interface CountryRepository {

    suspend fun readCountries(): List<Country>
}
package com.github.nkuppan.country.domain.repository

import com.github.nkuppan.country.domain.model.Country

interface CountryRepository {

    suspend fun readCountries(): List<Country>
}
package com.nkuppan.countrycompose.presentation.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nkuppan.country.domain.model.Country
import com.nkuppan.country.domain.repository.CountryRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Created by ancientinc on 2019-07-06.
 **/
class CountryListViewModel(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _countryList = MutableStateFlow<List<Country>>(emptyList())
    val countryList: StateFlow<List<Country>> = _countryList.asStateFlow()

    private var currentJob: Job? = null

    fun loadCountries(countryName: String? = "") {

        currentJob?.cancel()

        currentJob = viewModelScope.launch {

            val countryList = countryRepository.readCountries()

            val filteredList = mutableListOf<Country>()

            if (countryName?.isNotBlank() == true && countryList.isNotEmpty()) {
                filteredList.addAll(
                    countryList.filter {
                        it.name?.contains(countryName, ignoreCase = true) == true
                    }
                )
            } else {
                filteredList.addAll(countryList)
            }

            _countryList.value = filteredList
        }
    }
}
package com.nkuppan.country.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nkuppan.country.model.CountryModel
import com.nkuppan.country.utils.readCountryList
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by ancientinc on 2019-07-06.
 **/
class CountryListViewModel(application: Application) : AndroidViewModel(application) {

    private val _countryList = MutableLiveData<List<CountryModel>>()
    val countryList: LiveData<List<CountryModel>> = _countryList

    private var currentJob: Job? = null

    fun loadCountries(countryName: String? = "") {

        currentJob?.cancel()

        currentJob = viewModelScope.launch {

            val countryList = readCountryList(getApplication())

            val filteredList = mutableListOf<CountryModel>()

            if (countryName?.isNotBlank() == true && countryList?.isNotEmpty() == true) {
                filteredList.addAll(countryList.filter {
                    it.countryName?.contains(countryName, ignoreCase = true) == true
                })
            } else {
                if (countryList != null) {
                    filteredList.addAll(countryList)
                }
            }
            _countryList.value = filteredList
        }
    }
}
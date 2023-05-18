package com.nkuppan.countrycompose.presentation.country

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.gson.Gson
import com.nkuppan.country.data.repository.CountryRepositoryImpl
import com.nkuppan.country.data.repository.GsonJsonConverter

val ViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
    initializer {
        val application = this[APPLICATION_KEY]
        CountryListViewModel(
            CountryRepositoryImpl(
                application!!,
                GsonJsonConverter(Gson()),
            )
        )
    }
}


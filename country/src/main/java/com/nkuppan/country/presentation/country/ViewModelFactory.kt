package com.nkuppan.country.presentation.country

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.nkuppan.country.data.repository.CountryRepositoryImpl
import com.nkuppan.country.data.repository.GsonJsonConverter

class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass == CountryListViewModel::class.java) {
            CountryListViewModel(
                CountryRepositoryImpl(
                    application,
                    GsonJsonConverter(Gson()),
                )
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not found")
        }
    }
}


package com.nkuppan.country.presentation.country

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    var searchText = MutableLiveData<String>()

    var searchHintText = MutableLiveData<String>()

    val backNavigation = MutableLiveData<Unit>()

    val voiceSearch = MutableLiveData<Unit>()

    init {
        searchText.value = ""
    }

    fun voiceSearch() {
        voiceSearch.value = Unit
    }

    fun setSearchHintText() {
        voiceSearch.value = Unit
    }

    fun clearSearch() {
        searchText.value = ""
    }

    fun backNavigation() {
        backNavigation.value = Unit
    }
}

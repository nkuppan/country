package com.ancient.country.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * Created by ancientinc on 2019-07-06.
 **/
class SearchViewModel(aApplication: Application) : AndroidViewModel(aApplication) {

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

package com.ancient.country.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ancient.country.model.CountryModel
import com.ancient.country.utils.CountryReaderUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Created by ancientinc on 2019-07-06.
 **/
class CountryListViewModel(private val aApplication: Application) : AndroidViewModel(aApplication) {

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     * Since we pass viewModelJob, you can cancel all coroutines
     * launched by uiScope by calling viewModelJob.cancel()
     */
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _countryList = MutableLiveData<List<CountryModel>>()
    val countryList: LiveData<List<CountryModel>> = _countryList

    fun loadCountryList(aSearchValue: String? = "") {

        viewModelScope.launch {

            val lCountryList = CountryReaderUtils.readCountryList(aApplication)

            val lSearchedList = mutableListOf<CountryModel>()

            if (aSearchValue?.isNotBlank() == true && lCountryList?.isNotEmpty() == true) {
                lSearchedList.addAll(lCountryList.filter {
                    it.countryName?.contains(aSearchValue, ignoreCase = true) == true
                })
            } else {
                if (lCountryList != null) {
                    lSearchedList.addAll(lCountryList)
                }
            }
            _countryList.value = lSearchedList
        }
    }

}
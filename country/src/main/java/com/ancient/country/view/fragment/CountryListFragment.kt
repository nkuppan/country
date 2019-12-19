package com.ancient.country.view.fragment

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ancient.country.extention.autoCleared
import com.ancient.country.utils.RequestParam
import com.ancient.country.view.adapter.CountryListAdapter
import com.ancient.country.view.viewmodel.CountryListViewModel

/**
 * Created by ancientinc on 2019-07-06.
 **/
open class CountryListFragment : BaseListFragment() {

    private var viewModel by autoCleared<CountryListViewModel>()

    private var adapter by autoCleared<CountryListAdapter>()

    override fun createRequest(isStart: Boolean) {

        if (isStart) {

            viewModel = ViewModelProvider(this).get(CountryListViewModel::class.java)

            adapter = CountryListAdapter {
                val intent = Intent()
                intent.putExtra(RequestParam.SELECTED_VALUE, it)
                activity?.setResult(Activity.RESULT_OK, intent)
                activity?.finish()
            }

            setAdapter(adapter)

            viewModel.countryList.observe(this, Observer {

                if (it != null && it.isNotEmpty()) {
                    adapter.submitList(it)
                    setResult()
                } else {
                    adapter.submitList(mutableListOf())
                    setNoResult()
                }
            })
        }

        startLoadingRequest()

        viewModel.loadCountryList()
    }

    fun doSearch(aSearchValue: String) {
        viewModel.loadCountryList(aSearchValue)
    }
}
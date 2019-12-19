package com.ancient.country.view.activity

import android.os.Bundle
import com.ancient.country.R
import com.ancient.country.view.fragment.CountryListFragment

/**
 * Created by ancientinc on 2019-07-06.
 **/
class CountrySearchActivity : BaseSearchActivity() {

    private lateinit var countryListFragment: CountryListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        countryListFragment = CountryListFragment()

        attachThisFragment(countryListFragment)
    }

    override fun searchEntered(aSearchValue: String) {
        countryListFragment.doSearch(aSearchValue)
    }

    override fun getSearchHintText(): String {
        return getString(R.string.search_country)
    }
}
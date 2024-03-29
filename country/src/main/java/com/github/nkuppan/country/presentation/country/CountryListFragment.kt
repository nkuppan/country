package com.github.nkuppan.country.presentation.country

import android.app.Activity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.github.nkuppan.country.core.utils.promptSpeechInputForCallback
import com.github.nkuppan.country.core.view.BaseBindingFragment
import com.github.nkuppan.country.domain.model.Country
import com.nkuppan.country.databinding.FragmentCountryListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by ancientinc on 2019-07-06.
 **/
open class CountryListFragment : BaseBindingFragment<FragmentCountryListBinding>() {

    private val countryListViewModel: CountryListViewModel by viewModels { ViewModelFactory }

    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    private lateinit var adapter: CountryListAdapter

    var countrySelection: ((Country) -> Unit)? = null

    private val activityResultReceiver = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {

            val textList = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

            if (textList?.isNotEmpty() == true) {
                searchViewModel.searchText.value = textList[0]
            }
        }
    }

    override fun inflateLayout(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): FragmentCountryListBinding {
        return FragmentCountryListBinding.inflate(inflater)
    }

    override fun bindData(binding: FragmentCountryListBinding) {
        super.bindData(binding)
        binding.viewModel = searchViewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.itemAnimator = DefaultItemAnimator()
        binding.recyclerview.setHasFixedSize(true)

        searchViewModel.searchText.observe(viewLifecycleOwner) {
            processValue(it)
        }

        searchViewModel.backNavigation.observe(viewLifecycleOwner) {
            requireActivity().finish()
        }

        searchViewModel.voiceSearch.observe(viewLifecycleOwner) {
            promptSpeechInputForCallback(activityResultReceiver)
        }

        searchViewModel.searchHintText.value = getSearchHintText()

        createRequest()
    }

    private fun searchEntered(aSearchValue: String) {
        countryListViewModel.loadCountries(aSearchValue)
    }

    private fun getSearchHintText(): String {
        return getString(com.github.nkuppan.country.core.R.string.search_country)
    }

    private fun processValue(it: String) {
        searchEntered(it)
    }

    private fun setNoResult() {
        binding.infoText.visibility = View.VISIBLE
    }

    private fun setResult() {
        binding.infoText.visibility = View.GONE
    }

    private fun setAdapter(aAdapter: RecyclerView.Adapter<*>) {
        binding.recyclerview.adapter = aAdapter
    }

    private fun createRequest() {

        adapter = CountryListAdapter {
            countrySelection?.invoke(it)
        }

        setAdapter(adapter)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                countryListViewModel.countryList.collectLatest {
                    if (it.isNotEmpty()) {
                        adapter.submitList(it)
                        setResult()
                    } else {
                        adapter.submitList(mutableListOf())
                        setNoResult()
                    }
                }
            }
        }

        countryListViewModel.loadCountries()
    }
}
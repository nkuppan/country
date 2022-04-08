package com.nkuppan.country.view.fragment

import android.app.Activity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nkuppan.country.R
import com.nkuppan.country.databinding.FragmentCountryListBinding
import com.nkuppan.country.extention.autoCleared
import com.nkuppan.country.extention.promptSpeechInputForCallback
import com.nkuppan.country.model.CountryModel
import com.nkuppan.country.view.adapter.CountryListAdapter
import com.nkuppan.country.view.viewmodel.CountryListViewModel
import com.nkuppan.country.view.viewmodel.SearchViewModel


/**
 * Created by ancientinc on 19/05/20.
 **/
class CountryListBottomSheet : BottomSheetDialogFragment() {

    private val countryListViewModel: CountryListViewModel by lazy {
        ViewModelProvider(this).get(CountryListViewModel::class.java)
    }

    private var binding: FragmentCountryListBinding by autoCleared()

    private var searchViewModel: SearchViewModel by autoCleared()

    private var adapter: CountryListAdapter by autoCleared()

    var countrySelection: ((CountryModel) -> Unit)? = null

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_country_list, container, false)
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding = FragmentCountryListBinding.bind(view)
        binding.viewModel = searchViewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.itemAnimator = DefaultItemAnimator()
        binding.recyclerview.setHasFixedSize(true)

        searchViewModel.searchText.observe(viewLifecycleOwner) {
            processValue(it)
        }

        searchViewModel.backNavigation.observe(viewLifecycleOwner) {
            dismiss()
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
        return getString(R.string.search_country)
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

        countryListViewModel.countryList.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                adapter.submitList(it)
                setResult()
            } else {
                adapter.submitList(mutableListOf())
                setNoResult()
            }
        }

        countryListViewModel.loadCountries()
    }
}
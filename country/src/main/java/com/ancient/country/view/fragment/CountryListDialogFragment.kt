package com.ancient.country.view.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ancient.country.BuildConfig
import com.ancient.country.R
import com.ancient.country.databinding.DialogCountrySearchBinding
import com.ancient.country.extention.autoCleared
import com.ancient.country.model.CountryModel
import com.ancient.country.view.adapter.CountryListAdapter
import com.ancient.country.view.viewmodel.CountryListViewModel
import com.ancient.country.view.viewmodel.SearchViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

/**
 * Created by ancientinc on 11/05/20.
 **/
class CountryListDialogFragment : DialogFragment(), SwipeRefreshLayout.OnRefreshListener {

    private var mRecyclerView by autoCleared<RecyclerView>()
    private var mLoaderWidget by autoCleared<TextView>()
    private var mSwipeRefreshLayout by autoCleared<SwipeRefreshLayout>()
    private var mActionButton by autoCleared<FloatingActionButton>()

    private var countrySearchModel: CountryListViewModel by autoCleared()
    private var searchViewModel: SearchViewModel by autoCleared()

    private var adapter: CountryListAdapter by autoCleared()

    var countrySelection: ((CountryModel) -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.dialog_country_search, container, false)

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        val binding = DialogCountrySearchBinding.bind(view)
        binding.viewModel = searchViewModel

        binding.lifecycleOwner = this.viewLifecycleOwner

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView(view)

        searchViewModel.searchText.observe(viewLifecycleOwner, Observer {
            processValue(it)
        })

        searchViewModel.backNavigation.observe(viewLifecycleOwner, Observer {
            dismiss()
        })

        searchViewModel.voiceSearch.observe(viewLifecycleOwner, Observer {
            promptSpeechInput()
        })

        searchViewModel.searchHintText.value = getSearchHintText()

        createRequest(true)
    }

    private fun searchEntered(aSearchValue: String) {
        countrySearchModel.loadCountryList(aSearchValue)
    }

    private fun getSearchHintText(): String {
        return getString(R.string.search_country)
    }

    private fun processValue(it: String) {
        searchEntered(it)
    }

    /**
     * Showing google speech input dialog
     */
    private fun promptSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt))
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, a.localizedMessage ?: "")
            }
        }
    }

    private fun initializeView(aView: View) {

        mRecyclerView = aView.findViewById(R.id.recyclerview)
        mLoaderWidget = aView.findViewById(R.id.loader_widget)
        mSwipeRefreshLayout = aView.findViewById(R.id.refresh_layout)
        mActionButton = aView.findViewById(R.id.action_button)

        mRecyclerView.layoutManager = GridLayoutManager(context, 1)
        mRecyclerView.itemAnimator = DefaultItemAnimator()

        mSwipeRefreshLayout.setOnRefreshListener(this)

        hideActionButton()
    }

    fun setHasFixedSize(aFixedSize: Boolean) {
        mRecyclerView.setHasFixedSize(aFixedSize)
    }

    protected fun setActionIconDrawable(@DrawableRes aDrawable: Int) {
        mActionButton.setImageResource(aDrawable)
    }

    protected fun setActionClickListener(aClickListener: View.OnClickListener) {
        mActionButton.visibility = View.VISIBLE
        mActionButton.setOnClickListener(aClickListener)
    }

    protected fun hideActionButton() {
        mActionButton.visibility = View.GONE
    }

    protected fun startLoadingRequest() {
        mSwipeRefreshLayout.isRefreshing = true
    }

    protected fun setNoResult() {
        mSwipeRefreshLayout.isRefreshing = false
        mLoaderWidget.visibility = View.VISIBLE
    }

    protected fun setResult() {
        mSwipeRefreshLayout.isRefreshing = false
        mLoaderWidget.visibility = View.GONE
    }

    protected fun setAdapter(aAdapter: RecyclerView.Adapter<*>) {
        mRecyclerView.adapter = aAdapter
    }

    override fun onRefresh() {
        createRequest(isStart = false)
    }

    private fun createRequest(isStart: Boolean) {

        if (isStart) {

            countrySearchModel = ViewModelProvider(this).get(CountryListViewModel::class.java)

            adapter = CountryListAdapter {
                countrySelection?.invoke(it)
            }

            setAdapter(adapter)

            countrySearchModel.countryList.observe(viewLifecycleOwner, Observer {

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

        countrySearchModel.loadCountryList()
    }

    companion object {
        private const val REQ_CODE_SPEECH_INPUT = 100
        private const val TAG = "Search"
    }
}
package com.ancient.country.view.activity

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ancient.country.BuildConfig
import com.ancient.country.R
import com.ancient.country.databinding.ActivityCountrySearchBinding
import com.ancient.country.view.viewmodel.SearchViewModel
import java.util.*

/**
 * Created by ancientinc on 2019-07-06.
 */
abstract class BaseSearchActivity : BaseActivity() {

    private var viewModel: SearchViewModel? = null

    private var dataBinding: ActivityCountrySearchBinding? = null

    private var isInitialized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_country_search)

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        dataBinding?.lifecycleOwner = this

        dataBinding?.viewModel = viewModel

        viewModel?.searchText?.observe(this, Observer {

            if (!isInitialized) {
                isInitialized = true
                return@Observer
            }

            processValue(it)
        })

        viewModel?.backNavigation?.observe(this, Observer {
            onBackPressed()
        })

        viewModel?.voiceSearch?.observe(this, Observer {
            promptSpeechInput()
        })

        viewModel?.searchHintText?.value = getSearchHintText()
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

    /**
     * Receiving speech input
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {

                if (resultCode == Activity.RESULT_OK && null != data) {

                    val result = data.getStringArrayListExtra(
                            RecognizerIntent.EXTRA_RESULTS
                    )

                    if (result.isNullOrEmpty()) {
                        return
                    }

                    viewModel?.searchText?.value = result[0]
                }
            }
        }
    }

    protected fun attachThisFragment(aFragment: Fragment) {
        attachThisFragment(R.id.place_holder, aFragment)
    }

    companion object {
        private const val REQ_CODE_SPEECH_INPUT = 100
        private const val TAG = "Search"
    }

    abstract fun searchEntered(aSearchValue: String)

    abstract fun getSearchHintText(): String
}

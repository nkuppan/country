package com.nkuppan.example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.nkuppan.country.domain.model.Country
import com.nkuppan.country.utils.getCountryImage
import com.nkuppan.country.utils.getSelectedCountryData
import com.nkuppan.country.utils.isCountrySelectionResult
import com.nkuppan.country.utils.launchCountrySelectionActivity
import com.nkuppan.country.utils.openCountrySelectionBottomSheet
import com.nkuppan.country.utils.openCountrySelectionDialog
import com.nkuppan.example.databinding.MainActivityBinding

class XMLMainActivity : AppCompatActivity() {

    private var _binding: MainActivityBinding? = null
    private val binding: MainActivityBinding by lazy { _binding!! }

    private var isDarkTheme: Boolean = false

    private val countrySelectionReceiver = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {

            val country: Country? = result.getSelectedCountryData()

            if (country != null) {
                changeValues(country)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        binding.searchActivity.setOnClickListener {
            countrySelectionReceiver.launchCountrySelectionActivity(this)
        }

        binding.searchDialog.setOnClickListener {
            supportFragmentManager.openCountrySelectionDialog {
                changeValues(it)
            }
        }

        binding.searchBottomSheet.setOnClickListener {
            supportFragmentManager.openCountrySelectionBottomSheet {
                changeValues(it)
            }
        }

        binding.switchTheme.setOnClickListener {

            isDarkTheme = !isDarkTheme

            AppCompatDelegate.setDefaultNightMode(
                if (isDarkTheme)
                    AppCompatDelegate.MODE_NIGHT_YES
                else
                    AppCompatDelegate.MODE_NIGHT_NO
            )
        }
    }

    private fun changeValues(country: Country?) {

        country ?: return

        binding.countryName.text = buildString {
            append("Name\t\t : ")
            append(country.name)
        }

        binding.countryImage.setImageDrawable(
            country.getCountryImage(this@XMLMainActivity)
        )

        binding.countryCode.text = buildString {
            append("Code\t\t : ")
            append(country.countryCode)
        }

        binding.countryDialCode.text = buildString {
            append("Dial Code\t\t : ")
            append(country.dialCode)
        }

        binding.countryCurrencyCode.text = buildString {
            append("Currency Code\t\t : ")
            append(country.currencyCode)
        }

        binding.countrySymbol.text = buildString {
            append("Currency Symbol\t\t : ")
            append(country.currency?.symbol ?: "[ NONE ]")
        }

        Snackbar.make(
            binding.root,
            buildString {
                append("Selected Country [ name, code ] [")
                append(country.name)
                append(" , ")
                append(country.countryCode)
                append("]")
            },
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (isCountrySelectionResult(requestCode, resultCode)) {
            val country: Country? = data?.getSelectedCountryData()

            if (country != null) {
                changeValues(country)
            }
        }
    }
}
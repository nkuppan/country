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

        binding.countryName.text = "Name\t\t : ${country.name}"
        binding.countryImage.setImageDrawable(country.getCountryImage(this@XMLMainActivity))
        binding.countryCode.text = "Code\t\t : ${country.countryCode}"
        binding.countryDialCode.text = "Dial Code\t\t : ${country.dialCode}"
        binding.countryCurrencyCode.text = "Currency Code\t\t : ${country.currencyCode}"
        binding.countrySymbol.text =
            "Currency Symbol\t\t : ${country.currency?.symbol ?: "[ NONE ]"}"

        Snackbar.make(
            binding.root,
            "Selected Country [ name, code ] [${country.name} , ${country.countryCode}]",
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
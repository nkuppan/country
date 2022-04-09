package com.nkuppan.example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.nkuppan.country.extention.getCountryImage
import com.nkuppan.country.model.CountryModel
import com.nkuppan.country.utils.RequestParam
import com.nkuppan.country.view.activity.CountrySearchActivity
import com.nkuppan.country.view.fragment.CountryListBottomSheet
import com.nkuppan.country.view.fragment.CountryListDialogFragment
import com.nkuppan.example.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private var _binding: MainActivityBinding? = null
    private val binding: MainActivityBinding by lazy { _binding!! }

    private var isDarkTheme: Boolean = false

    private val countrySelectionReceiver = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {

            val country: CountryModel? =
                result.data?.getParcelableExtra(RequestParam.SELECTED_VALUE)

            if (country != null) {
                changeValues(country)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        findViewById<Button>(R.id.search_activity).setOnClickListener {
            countrySelectionReceiver.launch(
                Intent(this, CountrySearchActivity::class.java)
            )
        }

        binding.searchDialog.setOnClickListener {
            val ft = supportFragmentManager.beginTransaction()
            val countryListDialogFragment = CountryListDialogFragment()
            countryListDialogFragment.countrySelection = {
                changeValues(it)
                countryListDialogFragment.dismiss()
            }
            countryListDialogFragment.show(ft, "dialog")
        }

        binding.searchBottomSheet.setOnClickListener {
            val ft = supportFragmentManager.beginTransaction()
            val countryListBottomSheet = CountryListBottomSheet()
            countryListBottomSheet.countrySelection = {
                changeValues(it)
                countryListBottomSheet.dismiss()
            }
            countryListBottomSheet.show(ft, "bottom_sheet_dialog")
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

    private fun changeValues(country: CountryModel?) {

        country ?: return

        binding.countryName.text = "Name\t\t : ${country.countryName}"
        binding.countryImage.setImageDrawable(country.getCountryImage(this@MainActivity))
        binding.countryCode.text = "Code\t\t : ${country.countryCode}"
        binding.countryDialCode.text = "Dial Code\t\t : ${country.dialCode}"
        binding.countryCurrencyCode.text = "Currency Code\t\t : ${country.currencyCode}"
        binding.countrySymbol.text =
            "Currency Symbol\t\t : ${country.currency?.symbol ?: "[ NONE ]"}"

        Snackbar.make(
            binding.root,
            "Selected Country [ name, code ] [${country.countryName} , ${country.countryCode}]",
            Snackbar.LENGTH_SHORT
        ).show()
    }
}
package com.ancient.example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ancient.country.extention.autoCleared
import com.ancient.country.model.CountryModel
import com.ancient.country.utils.RequestCode
import com.ancient.country.utils.RequestParam
import com.ancient.country.view.activity.CountrySearchActivity
import com.ancient.country.view.fragment.CountryListDialog
import com.ancient.example.databinding.MainActivityBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var dataBinding: MainActivityBinding by autoCleared()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        dataBinding.searchActivity.setOnClickListener {
            startActivityForResult(
                    Intent(this@MainActivity, CountrySearchActivity::class.java),
                    RequestCode.COUNTRY_SEARCH_CODE
            )
        }

        dataBinding.searchDialog.setOnClickListener {
            val ft = supportFragmentManager.beginTransaction()
            val dialogFragment = CountryListDialog()
            dialogFragment.countrySelection = {
                changeValues(it)
                dialogFragment.dismiss()
            }
            dialogFragment.show(ft, "dialog")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RequestCode.COUNTRY_SEARCH_CODE && resultCode == Activity.RESULT_OK) {

            data?.apply {

                val country: CountryModel? = getParcelableExtra(RequestParam.SELECTED_VALUE)

                changeValues(country)
            }
        }
    }

    private fun changeValues(country: CountryModel?) {

        if (country != null) {

            dataBinding.countryName.text = "Name\t\t : ${country.countryName}"
            dataBinding.countryImage.setImageDrawable(country.getImage(this@MainActivity))
            dataBinding.countryCode.text = "Code\t\t : ${country.countryCode}"
            dataBinding.countryDialCode.text = "Dial Code\t\t : ${country.dialCode}"
            dataBinding.countryCurrencyCode.text = "Currency Code\t\t : ${country.currencyCode}"
            dataBinding.countrySymbol.text = "Currency Symbol\t\t : ${country.currency?.symbol ?: "[ NONE ]"}"

            Snackbar.make(
                    dataBinding.root,
                    "Selected Country [ name, code ] [${country.countryName} , ${country.countryCode}]",
                    Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}
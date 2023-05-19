package com.github.nkuppan.country.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.FragmentManager
import com.github.nkuppan.country.domain.model.Country
import com.github.nkuppan.country.presentation.country.CountryListBottomSheet
import com.github.nkuppan.country.presentation.country.CountryListDialogFragment
import com.github.nkuppan.country.presentation.country.CountrySearchActivity

fun FragmentManager.openCountrySelectionDialog(
    tag: String = "country_selection_dialog",
    callback: ((Country) -> Unit)? = null
) {
    val countryListDialogFragment = CountryListDialogFragment()
    countryListDialogFragment.countrySelection = {
        callback?.invoke(it)
        countryListDialogFragment.dismiss()
    }
    countryListDialogFragment.show(this.beginTransaction(), tag)
}

fun FragmentManager.openCountrySelectionBottomSheet(
    tag: String = "bottom_sheet_dialog",
    callback: ((Country) -> Unit)? = null
) {
    val countryListBottomSheet = CountryListBottomSheet()
    countryListBottomSheet.countrySelection = {
        callback?.invoke(it)
        countryListBottomSheet.dismiss()
    }
    countryListBottomSheet.show(this.beginTransaction(), tag)
}

fun ActivityResultLauncher<Intent>.launchCountrySelectionActivity(context: Context) {
    launch(Intent(context, CountrySearchActivity::class.java))
}

fun Activity.launchCountrySelectionActivity() {
    this.startActivityForResult(
        Intent(this, CountrySearchActivity::class.java),
        RequestCode.COUNTRY_SEARCH_CODE
    )
}

fun isCountrySelectionResult(resultCode: Int, requestCode: Int): Boolean {
    return resultCode == Activity.RESULT_OK &&
            requestCode == RequestCode.COUNTRY_SEARCH_CODE
}
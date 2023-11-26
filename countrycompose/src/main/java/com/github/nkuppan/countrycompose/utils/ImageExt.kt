package com.github.nkuppan.countrycompose.utils

import android.annotation.SuppressLint
import android.content.Context
import com.github.nkuppan.country.core.R

@SuppressLint("DiscouragedApi")
fun String?.getCountryImage(context: Context): Int {
    try {
        val countryCode = this ?: ""

        var toLowerCase = countryCode.lowercase()

        if (toLowerCase == "do") {
            toLowerCase = "ic_do"
        }

        if (toLowerCase.contains("-".toRegex())) {
            toLowerCase = toLowerCase.replace("-".toRegex(), "_")
        }

        val resourcePackageName = context.resources
            .getResourcePackageName(R.drawable.ic_image)

        return context.resources.getIdentifier(
            toLowerCase,
            "drawable",
            resourcePackageName
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return R.drawable.ic_image
}
package com.github.nkuppan.countrycompose.utils

import android.annotation.SuppressLint
import android.content.Context
import com.github.nkuppan.country.core.R
import com.github.nkuppan.country.domain.model.Country
import java.util.Locale

@SuppressLint("DiscouragedApi")
fun Country.getCountryImage(context: Context): Int {
    try {
        var toLowerCase = "$countryCode".lowercase(Locale.ENGLISH)

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
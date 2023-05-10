package com.nkuppan.country.core.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.nkuppan.country.R
import com.nkuppan.country.domain.model.Country
import java.util.*


fun Country.getCountryImage(context: Context): Drawable? {
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

        return ContextCompat.getDrawable(
            context,
            context.resources.getIdentifier(
                toLowerCase,
                "drawable",
                resourcePackageName
            )
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return ContextCompat.getDrawable(context, R.drawable.ic_image)
}
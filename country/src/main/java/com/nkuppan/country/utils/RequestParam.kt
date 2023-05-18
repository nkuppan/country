package com.nkuppan.country.utils

import android.content.Intent
import android.os.Build
import androidx.activity.result.ActivityResult
import androidx.annotation.ChecksSdkIntAtLeast
import com.nkuppan.country.domain.model.Country

internal object RequestParam {
    const val SELECTED_VALUE = "selected_value"
}

internal object RequestCode {
    const val COUNTRY_SEARCH_CODE = 1000
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
private fun isTiramisuAndAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

fun <T : Any> Intent.getParcelableValue(key: String, clazz: Class<T>): T? {
    return if (isTiramisuAndAbove()) {
        getParcelableExtra(key, clazz)
    } else {
        getParcelableExtra(key)
    }
}

fun ActivityResult.getSelectedCountryData(): Country? {
    return data?.getParcelableValue(
        RequestParam.SELECTED_VALUE,
        Country::class.java
    )
}

fun Intent.getSelectedCountryData(): Country? {
    return getParcelableValue(
        RequestParam.SELECTED_VALUE,
        Country::class.java
    )
}
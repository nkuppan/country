package com.ancient.country.utils

import androidx.annotation.IntDef

/**
 * Created by ancientinc on 11/05/20.
 **/

@IntDef(
        SortMode.BY_COUNTRY_NAME,
        SortMode.BY_COUNTRY_CODE,
        SortMode.BY_COUNTRY_DIAL_CODE
)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class SortMode {
    companion object {
        const val BY_COUNTRY_NAME = 0
        const val BY_COUNTRY_CODE = BY_COUNTRY_NAME + 1
        const val BY_COUNTRY_DIAL_CODE = BY_COUNTRY_CODE + 1
    }
}

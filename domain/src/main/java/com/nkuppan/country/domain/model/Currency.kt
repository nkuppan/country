package com.nkuppan.country.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    @SerializedName("symbol")
    var symbol: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("symbol_native")
    var nativeSymbol: String? = null,
    @SerializedName("decimal_digits")
    var decimalDigits: Int = 0,
    @SerializedName("rounding")
    var rounding: Float = 0f,
    @SerializedName("name_plural")
    var namePlural: String? = null,
    @SerializedName("code")
    var code: String? = null,
): Parcelable
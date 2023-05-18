package com.nkuppan.country.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("code")
    var countryCode: String? = null,
    @SerializedName("dial_code")
    var dialCode: String? = null,
    @SerializedName("continent")
    var continent: String? = null,
    @SerializedName("group")
    var countryGroup: String? = null,
    @SerializedName("states")
    var countryStates: String? = null,
    @SerializedName("currency_code")
    var currencyCode: String? = null,
    @SerializedName("currency")
    var currency: Currency? = null,
) : Parcelable
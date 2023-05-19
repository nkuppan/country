package com.github.nkuppan.country.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Currency::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(countryCode)
        parcel.writeString(dialCode)
        parcel.writeString(continent)
        parcel.writeString(countryGroup)
        parcel.writeString(countryStates)
        parcel.writeString(currencyCode)
        parcel.writeParcelable(currency, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Country> {
        override fun createFromParcel(parcel: Parcel): Country {
            return Country(parcel)
        }

        override fun newArray(size: Int): Array<Country?> {
            return arrayOfNulls(size)
        }
    }
}
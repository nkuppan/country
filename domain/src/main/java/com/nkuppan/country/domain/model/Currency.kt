package com.nkuppan.country.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Currency(
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
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(symbol)
        parcel.writeString(name)
        parcel.writeString(nativeSymbol)
        parcel.writeInt(decimalDigits)
        parcel.writeFloat(rounding)
        parcel.writeString(namePlural)
        parcel.writeString(code)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Currency> {
        override fun createFromParcel(parcel: Parcel): Currency {
            return Currency(parcel)
        }

        override fun newArray(size: Int): Array<Currency?> {
            return arrayOfNulls(size)
        }
    }

}
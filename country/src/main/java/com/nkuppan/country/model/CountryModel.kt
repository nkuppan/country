package com.nkuppan.country.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by ancientinc on 2019-07-06.
 **/
open class CountryModel() : Parcelable {

    @SerializedName("name")
    var countryName: String? = null

    @SerializedName("code")
    var countryCode: String? = null

    @SerializedName("dial_code")
    var dialCode: String? = null

    @SerializedName("continent")
    var continent: String? = null

    @SerializedName("group")
    var countryGroup: String? = null

    @SerializedName("states")
    var countryStates: String? = null

    @SerializedName("currency_code")
    var currencyCode: String? = null

    @SerializedName("currency")
    var currency: CurrencyModel? = null

    constructor(parcel: Parcel) : this() {
        countryName = parcel.readString()
        countryCode = parcel.readString()
        dialCode = parcel.readString()
        continent = parcel.readString()
        countryGroup = parcel.readString()
        countryStates = parcel.readString()
        currencyCode = parcel.readString()
        currency = parcel.readParcelable(CurrencyModel::class.java.classLoader)
    }

    fun getStates(): MutableList<String> {

        val lValue = countryStates?.split("|")

        val lStateList = mutableListOf<String>()

        if (lValue != null && lValue.isNotEmpty()) {
            lStateList.addAll(lValue.toMutableList())
        }

        return lStateList
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(countryName)
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

    companion object CREATOR : Parcelable.Creator<CountryModel> {
        override fun createFromParcel(parcel: Parcel): CountryModel {
            return CountryModel(parcel)
        }

        override fun newArray(size: Int): Array<CountryModel?> {
            return arrayOfNulls(size)
        }
    }
}

open class CurrencyModel() : Parcelable {

    @SerializedName("symbol")
    var symbol: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("symbol_native")
    var nativeSymbol: String? = null

    @SerializedName("decimal_digits")
    var decimalDigits: Int = 0

    @SerializedName("rounding")
    var rounding: Float = 0f

    @SerializedName("name_plural")
    var namePlural: String? = null

    @SerializedName("code")
    var code: String? = null

    constructor(parcel: Parcel) : this() {
        symbol = parcel.readString()
        name = parcel.readString()
        nativeSymbol = parcel.readString()
        decimalDigits = parcel.readInt()
        rounding = parcel.readFloat()
        namePlural = parcel.readString()
        code = parcel.readString()
    }

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

    companion object CREATOR : Parcelable.Creator<CurrencyModel> {
        override fun createFromParcel(parcel: Parcel): CurrencyModel {
            return CurrencyModel(parcel)
        }

        override fun newArray(size: Int): Array<CurrencyModel?> {
            return arrayOfNulls(size)
        }
    }
}
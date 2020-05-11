package com.ancient.country.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import androidx.core.content.ContextCompat
import com.ancient.country.R
import com.google.gson.annotations.SerializedName
import java.util.*

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

    @SerializedName("group")
    var countryGroup: String? = null

    @SerializedName("states")
    var countryStates: String? = null

    constructor(parcel: Parcel) : this() {
        countryName = parcel.readString()
        countryCode = parcel.readString()
        dialCode = parcel.readString()
        countryGroup = parcel.readString()
        countryStates = parcel.readString()
    }

    fun getStates(): MutableList<String> {

        val lValue = countryStates?.split("|")

        val lStateList = mutableListOf<String>()

        if (lValue != null && lValue.isNotEmpty()) {
            lStateList.addAll(lValue.toMutableList())
        }

        return lStateList
    }

    fun getImage(aContext: Context): Drawable? {
        try {
            var toLowerCase = "$countryCode".toLowerCase(Locale.ENGLISH)

            if (toLowerCase == "do") {
                toLowerCase = "ic_do"
            }

            val resourcePackageName = aContext.resources
                    .getResourcePackageName(R.drawable.ic_image)

            return ContextCompat.getDrawable(
                    aContext,
                    aContext.resources.getIdentifier(
                            toLowerCase,
                            "drawable",
                            resourcePackageName
                    )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ContextCompat.getDrawable(aContext, R.drawable.ic_image)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(countryName)
        parcel.writeString(countryCode)
        parcel.writeString(dialCode)
        parcel.writeString(countryGroup)
        parcel.writeString(countryStates)
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
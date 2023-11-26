package com.github.nkuppan.countrycompose.utils

import android.content.Context
import com.github.nkuppan.country.domain.model.Country
import com.nkuppan.countrycompose.R

fun Country.getCurrencyName(context: Context): String {
    val currency = this.currency

    currency ?: return this.currencyCode ?: ""

    val currencyName = if (currency.name?.isNotBlank() == true) {
        currency.name
    } else if (currency.namePlural?.isNotBlank() == true) {
        currency.namePlural
    } else {
        this.name
    }

    val currencySymbol = if (currency.symbol?.isNotBlank() == true) {
        currency.symbol
    } else if (currency.nativeSymbol?.isNotBlank() == true) {
        currency.nativeSymbol
    } else {
        this.countryCode
    }

    return context.getString(R.string.currency_description, currencyName, currencySymbol)
}
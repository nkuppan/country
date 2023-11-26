package com.github.nkuppan.countrycompose.presentation.currency

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.github.nkuppan.country.domain.model.Country
import com.github.nkuppan.country.domain.model.Currency
import com.github.nkuppan.countrycompose.presentation.country.CountryListViewModel
import com.github.nkuppan.countrycompose.ui.components.CountrySearchView
import com.github.nkuppan.countrycompose.ui.components.CountryWithCurrencyItemView
import com.github.nkuppan.countrycompose.ui.theme.CountryAppTheme
import com.github.nkuppan.countrycompose.utils.getCountryImage
import com.github.nkuppan.countrycompose.utils.getCurrencyName

@Composable
internal fun CountryCurrencyListAndSearchView(
    modifier: Modifier = Modifier,
    countryListViewModel: CountryListViewModel,
    dismiss: (() -> Unit)? = null,
    selection: ((Country) -> Unit)?
) {

    val countries by countryListViewModel.countryList.collectAsState()

    CountryCurrencyListAndSearchView(
        countries = countries,
        selection = selection,
        modifier = modifier,
        searchCallback = countryListViewModel::loadCountries,
        dismiss = dismiss
    )
}

@Composable
internal fun CountryCurrencyListAndSearchView(
    countries: List<Country>,
    selection: ((Country) -> Unit)?,
    modifier: Modifier = Modifier,
    searchCallback: ((String) -> Unit)? = null,
    dismiss: (() -> Unit)? = null
) {
    Scaffold(
        topBar = {
            CountrySearchView(
                dismiss,
                searchCallback
            )
        },
    ) {
        CountryCurrencyListView(
            countries = countries,
            selection = selection,
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Composable
internal fun CountryCurrencyListView(
    countries: List<Country>,
    selection: ((Country) -> Unit)?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LazyColumn(modifier = modifier) {
        items(countries) {
            CountryWithCurrencyItemView(
                name = it.name ?: "",
                flagImage = it.countryCode.getCountryImage(context),
                modifier = Modifier.clickable {
                    selection?.invoke(it)
                },
                description = it.getCurrencyName(context)
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun CountryDetailsPreview() {
    CountryAppTheme(isDarkTheme = true) {
        CountryCurrencyListAndSearchView(
            countries = listOf(
                Country("India", "in", "", currency = Currency(name = "Rupees", symbol = "₹")),
                Country("USA", "us", "", currency = Currency(name = "Rupees", symbol = "$")),
            ),
            selection = {

            }
        )
    }
}
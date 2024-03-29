package com.github.nkuppan.countrycompose.presentation.country

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
import com.github.nkuppan.countrycompose.ui.components.CountryItemView
import com.github.nkuppan.countrycompose.ui.components.CountrySearchView
import com.github.nkuppan.countrycompose.ui.theme.CountryAppTheme
import com.github.nkuppan.countrycompose.utils.getCountryImage

@Composable
internal fun CountryListAndSearchView(
    modifier: Modifier = Modifier,
    countryListViewModel: CountryListViewModel,
    dismiss: (() -> Unit)? = null,
    selection: ((Country) -> Unit)?
) {

    val countries by countryListViewModel.countryList.collectAsState()

    CountryListAndSearchView(
        countries = countries,
        selection = selection,
        modifier = modifier,
        searchCallback = countryListViewModel::loadCountries,
        dismiss = dismiss
    )
}

@Composable
internal fun CountryListAndSearchView(
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
        CountryListView(
            countries = countries,
            selection = selection,
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Composable
internal fun CountryListView(
    countries: List<Country>,
    selection: ((Country) -> Unit)?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LazyColumn(modifier = modifier) {
        items(countries) {
            CountryItemView(
                modifier = Modifier.clickable {
                    selection?.invoke(it)
                },
                name = it.name ?: "",
                flagImage = it.countryCode.getCountryImage(context)
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun CountryDetailsPreview() {
    CountryAppTheme(isDarkTheme = true) {
        CountryListAndSearchView(
            countries = listOf(
                Country("India", "in", ""),
                Country("USA", "us", ""),
            ),
            selection = {

            }
        )
    }
}
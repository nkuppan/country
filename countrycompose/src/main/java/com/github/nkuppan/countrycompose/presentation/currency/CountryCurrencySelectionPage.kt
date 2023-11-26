package com.github.nkuppan.countrycompose.presentation.currency

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.nkuppan.country.domain.model.Country
import com.github.nkuppan.countrycompose.presentation.country.CountryListViewModel
import com.github.nkuppan.countrycompose.presentation.country.ViewModelFactory
import com.github.nkuppan.countrycompose.ui.theme.CountryAppTheme


@Composable
fun CountryCurrencySelectionPage(
    modifier: Modifier = Modifier,
    countryListViewModel: CountryListViewModel = viewModel(factory = ViewModelFactory),
    onDismissRequest: (() -> Unit)? = null,
    selection: ((Country) -> Unit)? = null
) {
    CountryAppTheme {
        CountryCurrencyListAndSearchView(
            modifier,
            countryListViewModel,
            onDismissRequest,
            selection
        )
    }
}
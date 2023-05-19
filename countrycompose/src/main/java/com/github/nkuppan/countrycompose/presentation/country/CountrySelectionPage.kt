package com.github.nkuppan.countrycompose.presentation.country

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.nkuppan.country.domain.model.Country


@Preview
@Composable
fun CountrySelectionPage(
    modifier: Modifier = Modifier,
    countryListViewModel: CountryListViewModel = viewModel(factory = ViewModelFactory),
    onDismissRequest: (() -> Unit)? = null,
    selection: ((Country) -> Unit)? = null
) {
    countryListViewModel.loadCountries()

    CountrySearchAndListView(
        modifier,
        countryListViewModel,
        onDismissRequest,
        selection
    )
}


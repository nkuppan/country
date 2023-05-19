package com.github.nkuppan.countrycompose.presentation.country

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.nkuppan.country.domain.model.Country

@Composable
fun CountrySelectionDialog(
    modifier: Modifier = Modifier,
    countryListViewModel: CountryListViewModel = viewModel(factory = ViewModelFactory),
    onDismissRequest: (() -> Unit)? = null,
    selection: ((Country) -> Unit)? = null
) {

    countryListViewModel.loadCountries()

    Dialog(
        onDismissRequest = {
            onDismissRequest?.invoke()
        }
    ) {
        CountrySearchAndListView(
            modifier,
            countryListViewModel,
            onDismissRequest,
            selection
        )
    }
}


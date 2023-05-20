package com.github.nkuppan.countrycompose.presentation.country

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.nkuppan.country.core.R
import com.github.nkuppan.country.domain.model.Country
import com.github.nkuppan.countrycompose.ui.theme.CountryAppTheme
import com.github.nkuppan.countrycompose.utils.getCountryImage

@Composable
internal fun CountrySearchView(
    dismiss: (() -> Unit)? = null,
    searchCallback: ((String) -> Unit)? = null
) {

    var showClear by remember { mutableStateOf(false) }

    var searchText by remember { mutableStateOf("") }

    Surface(
        elevation = 8.dp
    ) {
        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp, top = 4.dp)) {
            OutlinedTextField(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .weight(1f),
                value = searchText,
                onValueChange = {
                    searchText = it
                    showClear = it.isNotBlank()
                    searchCallback?.invoke(it)
                },
                label = {
                    Text(
                        modifier = Modifier.align(
                            Alignment.CenterVertically
                        ),
                        text = stringResource(
                            id = R.string.search_country
                        ),
                        textAlign = TextAlign.Center
                    )
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp)
                            .align(Alignment.CenterVertically)
                            .clickable {
                                dismiss?.invoke()
                            },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = ""
                    )
                },
                trailingIcon = {
                    if (showClear) {
                        Icon(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(24.dp)
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    searchText = ""
                                    showClear = false
                                    searchCallback?.invoke("")
                                },
                            imageVector = Icons.Default.Close,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    }
}

@Composable
internal fun CountrySearchAndListView(
    modifier: Modifier = Modifier,
    countryListViewModel: CountryListViewModel,
    dismiss: (() -> Unit)? = null,
    selection: ((Country) -> Unit)?
) {

    val countries by countryListViewModel.countryList.collectAsState()

    CountrySearchAndListView(
        modifier,
        countries,
        countryListViewModel::loadCountries,
        dismiss,
        selection
    )
}

@Composable
internal fun CountrySearchAndListView(
    modifier: Modifier = Modifier,
    countries: List<Country>,
    searchCallback: ((String) -> Unit)? = null,
    dismiss: (() -> Unit)? = null,
    selection: ((Country) -> Unit)?
) {
    Scaffold(
        topBar = {
            CountrySearchView(
                dismiss,
                searchCallback
            )
        },
    ) {
        it.calculateTopPadding()
        Column(modifier = modifier.fillMaxSize()) {
            CountryListView(countries, selection)
        }
    }
}

@Composable
internal fun CountryListView(
    countries: List<Country>,
    selection: ((Country) -> Unit)?
) {
    val context = LocalContext.current

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(countries) {
            CountryDetailsView(
                modifier = Modifier.clickable {
                    selection?.invoke(it)
                },
                name = it.name ?: "",
                flagImage = it.getCountryImage(context)
            )
        }
    }
}

@Composable
internal fun CountryDetailsView(
    modifier: Modifier = Modifier,
    name: String,
    flagImage: Int
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(flagImage),
            contentDescription = ""
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth(),
            text = name,
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun CountryDetailsPreview() {
    CountryAppTheme(isDarkTheme = true) {
        CountrySearchAndListView(
            countries = listOf(
                Country("India", "in", ""),
                Country("USA", "us", ""),
            ),
            selection = {

            }
        )
    }
}
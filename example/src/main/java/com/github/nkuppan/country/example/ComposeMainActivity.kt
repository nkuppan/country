package com.github.nkuppan.country.example

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.nkuppan.country.domain.model.Country
import com.github.nkuppan.countrycompose.presentation.country.CountrySelectionDialog
import com.github.nkuppan.countrycompose.presentation.country.CountrySelectionPage
import com.github.nkuppan.countrycompose.presentation.currency.CountryCurrencySelectionDialog
import com.github.nkuppan.countrycompose.presentation.currency.CountryCurrencySelectionPage
import com.github.nkuppan.countrycompose.utils.getCountryImage
import com.github.nkuppan.countrycompose.utils.getCurrencyName

private const val COUNTRY_SELECTION_AS_PAGE = "country_selection"
private const val COUNTRY_AND_CURRENCY_SELECTION_AS_PAGE = "country_and_currency_selection"

class ComposeMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navHostController = rememberNavController()

            var pageSelection by remember { mutableStateOf(PageSelection.NONE) }

            var message by remember { mutableStateOf<String?>(null) }

            var selectedCountry by remember { mutableStateOf<Country?>(null) }

            val scaffoldState = rememberScaffoldState()

            if (message != null) {
                LaunchedEffect(message, scaffoldState) {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = message!!, duration = SnackbarDuration.Short
                    )
                }
            }

            when (pageSelection) {
                PageSelection.SHOW_COUNTRY_SELECTION_PAGE -> {
                    navHostController.navigate(COUNTRY_SELECTION_AS_PAGE)
                }

                PageSelection.SHOW_COUNTRY_SELECTION_DIALOG -> {
                    CountrySelectionDialog(onDismissRequest = {
                        pageSelection = PageSelection.NONE
                    }) { country ->
                        pageSelection = PageSelection.NONE
                        selectedCountry = country
                        message = "Selected Country ${country.name} & ${country.countryCode}"
                    }
                }

                PageSelection.SHOW_COUNTRY_CURRENCY_SELECTION_DIALOG -> {
                    CountryCurrencySelectionDialog(onDismissRequest = {
                        pageSelection = PageSelection.NONE
                    }) { country ->
                        pageSelection = PageSelection.NONE
                        selectedCountry = country
                        message = "Selected Country ${country.name} & ${country.countryCode}"
                    }
                }

                PageSelection.SHOW_COUNTRY_CURRENCY_SELECTION_PAGE -> {
                    navHostController.navigate(COUNTRY_AND_CURRENCY_SELECTION_AS_PAGE)
                }

                else -> {
                    if (navHostController.previousBackStackEntry != null) {
                        navHostController.popBackStack()
                    }
                }
            }

            NavHost(
                navController = navHostController,
                startDestination = "home"
            ) {
                composable("home") {
                    HomePage(selectedCountry) {
                        pageSelection = it
                    }
                }
                composable(route = COUNTRY_SELECTION_AS_PAGE) {
                    CountrySelectionPage(
                        onPageSelection = {
                            pageSelection = it
                        },
                        onCountrySelection = {
                            selectedCountry = it
                        }
                    )
                }
                composable(route = COUNTRY_AND_CURRENCY_SELECTION_AS_PAGE) {
                    CountryAndCurrencySelectionPage(
                        onPageSelection = {
                            pageSelection = it
                        },
                        onCountrySelection = {
                            selectedCountry = it
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun HomePage(
    selectedCountry: Country?,
    onPageSelection: (PageSelection) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onPageSelection.invoke(PageSelection.SHOW_COUNTRY_SELECTION_DIALOG)
            }
        ) {
            Text(text = stringResource(id = R.string.country_search_as_dialog))
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onPageSelection.invoke(PageSelection.SHOW_COUNTRY_SELECTION_PAGE)
            }
        ) {
            Text(text = stringResource(id = R.string.country_search_as_page))
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onPageSelection.invoke(PageSelection.SHOW_COUNTRY_CURRENCY_SELECTION_DIALOG)
            }
        ) {
            Text(text = stringResource(id = R.string.country_and_currency_search_as_dialog))
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onPageSelection.invoke(PageSelection.SHOW_COUNTRY_CURRENCY_SELECTION_PAGE)
            }
        ) {
            Text(text = stringResource(id = R.string.country_and_currency_search_as_page))
        }

        CountryView(selectedCountry)
    }
}

@Composable
fun CountrySelectionPage(
    onPageSelection: (PageSelection) -> Unit,
    onCountrySelection: (Country) -> Unit
) {
    CountrySelectionPage(
        onDismissRequest = {
            onPageSelection.invoke(PageSelection.NONE)
        }
    ) { country ->
        onPageSelection.invoke(PageSelection.NONE)
        onCountrySelection.invoke(country)
    }
}

@Composable
fun CountryAndCurrencySelectionPage(
    onPageSelection: (PageSelection) -> Unit,
    onCountrySelection: (Country) -> Unit
) {
    CountryCurrencySelectionPage(
        onDismissRequest = {
            onPageSelection.invoke(PageSelection.NONE)
        }
    ) { country ->
        onPageSelection.invoke(PageSelection.NONE)
        onCountrySelection.invoke(country)
    }
}

enum class PageSelection {
    NONE,
    SHOW_COUNTRY_SELECTION_PAGE,
    SHOW_COUNTRY_SELECTION_DIALOG,
    SHOW_COUNTRY_CURRENCY_SELECTION_DIALOG,
    SHOW_COUNTRY_CURRENCY_SELECTION_PAGE
}

@Composable
private fun CountryView(selectedCountry: Country?) {

    val context = LocalContext.current

    if (selectedCountry != null) {
        Image(
            modifier = Modifier.size(
                96.dp
            ),
            painter = painterResource(
                id = selectedCountry.countryCode.getCountryImage(context)
            ),
            contentDescription = ""
        )
        Text(text = selectedCountry.name ?: "")
        Text(text = selectedCountry.countryCode ?: "")
        Text(text = selectedCountry.countryGroup ?: "")
        Text(text = selectedCountry.getCurrencyName(context))
    }
}
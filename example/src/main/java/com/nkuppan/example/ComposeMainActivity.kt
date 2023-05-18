package com.nkuppan.example

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.nkuppan.country.domain.model.Country
import com.nkuppan.countrycompose.presentation.country.CountrySelectionDialog
import com.nkuppan.countrycompose.presentation.country.CountrySelectionPage
import com.nkuppan.countrycompose.utils.getCountryImage

class ComposeMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var showDialog by remember {
                mutableStateOf(false)
            }

            var showPage by remember {
                mutableStateOf(false)
            }

            var message by remember {
                mutableStateOf<String?>(null)
            }

            var selectedCountry by remember {
                mutableStateOf<Country?>(null)
            }

            val scaffoldState = rememberScaffoldState()

            if (message != null) {
                LaunchedEffect(message, scaffoldState) {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = message!!, duration = SnackbarDuration.Short
                    )
                }
            }


            if (showPage) {
                CountrySelectionPage(onDismissRequest = {
                    showPage = false
                }) { country ->
                    showPage = false
                    selectedCountry = country
                    message = "Selected Country ${country.name} & ${country.countryCode}"
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    if (showDialog) {
                        CountrySelectionDialog(onDismissRequest = {
                            showDialog = false
                        }) { country ->
                            showDialog = false
                            selectedCountry = country
                            message =
                                "Selected Country ${country.name} & ${country.countryCode}"
                        }
                    }

                    Column {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                showDialog = true
                            }
                        ) {
                            Text(text = stringResource(id = R.string.country_search_as_dialog))
                        }
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                showPage = true
                            }
                        ) {
                            Text(text = stringResource(id = R.string.country_search_as_page))
                        }

                        CountryView(selectedCountry)
                    }
                }
            }
        }
    }
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
                id = selectedCountry.getCountryImage(context)
            ),
            contentDescription = ""
        )
        Text(text = selectedCountry.name ?: "")
        Text(text = selectedCountry.countryCode ?: "")
        Text(text = selectedCountry.countryGroup ?: "")
    }
}
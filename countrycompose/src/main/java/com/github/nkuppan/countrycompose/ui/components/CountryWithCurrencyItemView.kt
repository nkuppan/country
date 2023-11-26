package com.github.nkuppan.countrycompose.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.nkuppan.countrycompose.ui.theme.CountryAppTheme
import com.github.nkuppan.countrycompose.utils.getCountryImage


@Composable
internal fun CountryWithCurrencyItemView(
    name: String,
    flagImage: Int,
    description: String,
    modifier: Modifier = Modifier,
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
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth()
        ) {
            Text(text = name)
            Text(
                text = description,
                fontSize = 12.sp
            )
        }
    }
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun CountryWithCurrencyItemViewPreview() {
    val context = LocalContext.current
    CountryAppTheme {
        CountryWithCurrencyItemView(
            name = "India",
            flagImage = "in".getCountryImage(context),
            description = "Currency: Rupee (₹)"
        )
    }
}
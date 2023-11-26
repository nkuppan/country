package com.github.nkuppan.countrycompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
internal fun CountryItemView(
    name: String,
    flagImage: Int,
    modifier: Modifier = Modifier
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
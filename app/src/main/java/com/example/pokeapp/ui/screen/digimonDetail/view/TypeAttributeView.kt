package com.example.pokeapp.ui.screen.digimonDetail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokeapp.ui.theme.Pink40
import com.example.pokeapp.ui.theme.PurpleGrey40

@Composable
fun TypeAttributeView(
    types: String,
    attributes: String
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = PurpleGrey40,
                    shape = RoundedCornerShape(200.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .width(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                types,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }
        Box(
            modifier = Modifier
                .background(
                    color = Pink40,
                    shape = RoundedCornerShape(200.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .width(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                attributes,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }
    }
}
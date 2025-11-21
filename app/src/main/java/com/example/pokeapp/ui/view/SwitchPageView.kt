package com.example.pokeapp.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SwitchPageView(
    currentPage: Int,
    totalPage: Int,
    onPrevPage: () -> Unit,
    onNextPage: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                onPrevPage()
            },
            enabled = 1 < currentPage
        ) {
            Text(
                text = "Prev",
                style = MaterialTheme.typography.titleMedium
            )
        }
        Text(
            text = "Page $currentPage",
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.titleMedium
        )
        Button(
            onClick = {
                onNextPage()
            },
            enabled = currentPage < totalPage
        ) {
            Text(
                text = "Next",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
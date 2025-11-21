package com.example.pokeapp.ui.screen.pokemonDetail.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PokemonImageView(
    id: Int,
    imageUrl: String,
    name: String
) {
    val height = 200.dp

    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Box(
            modifier = Modifier
                .height(height)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
            )
            Box(
                modifier = Modifier
                    .height(height)
                    .fillMaxWidth()
                    .padding(end = 16.dp, bottom = 12.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = "ID: $id",
                    color = Color.White
                )
            }
        }
    }
}
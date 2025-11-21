package com.example.pokeapp.ui.screen.pokemonDetail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pokeapp.api.response.PokemonDetailResponse.Stat

@Composable
fun PokemonStatBarChart(
    statList: List<Stat>
) {
    val maxValue = 100f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        statList.forEach { stat ->

            val ratio = stat.base_stat / maxValue

            Column {
                Text(
                    text = stat.stat.name.replaceFirstChar { c -> c.uppercase() },
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 4.dp)
                )
                Spacer(Modifier.height(2.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Bar 本體（圓角 + 水平伸展）
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(24.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))  // 外框也要圓角
                            .background(Color(0xFFE0E0E0))   // 背景條（淡灰）
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(ratio)   // 依比例顯示長度
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.primary)
                        )
                    }
                    Spacer(Modifier.width(16.dp))
                    Text(
                        text = stat.base_stat.toString(),
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.width(30.dp)
                    )
                }
            }
        }
    }
}


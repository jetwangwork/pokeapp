package com.example.pokeapp.ui.screen.home.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MonsterListView(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    monsterNameList: List<String>,
    monsterImageUrlList: List<String>,
    onMonsterClicked: (index: Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 8.dp),
        modifier = modifier,
        state = listState
    ) {
        items(monsterNameList.size) { index ->
            val monsterName = monsterNameList[index]
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {
                        onMonsterClicked(index)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = monsterImageUrlList[index],
                        contentDescription = monsterName,
                        modifier = Modifier
                            .size(120.dp)
                            .padding(8.dp)
                    )
                    Text(
                        text = monsterName,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
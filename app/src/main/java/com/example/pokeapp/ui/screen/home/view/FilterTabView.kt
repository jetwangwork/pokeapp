package com.example.pokeapp.ui.screen.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.pokeapp.ui.screen.home.ShowType

@Composable
fun FilterTabView(
    showType: ShowType,
    onTypeChange: (type: ShowType) -> Unit,
    pageCount: Int,
    selectedPage: Int,
    onPageSelected: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        Spacer(Modifier.width(16.dp))
        FilterButton(showType, onTypeChange)
        Spacer(Modifier.width(8.dp))
        CircleTabBar(pageCount, selectedPage, onPageSelected)
    }
}

@Composable
fun FilterButton(
    showType: ShowType,
    onTypeChange: (type: ShowType) -> Unit,
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = {
                val type = when(showType) {
                    ShowType.Grid -> ShowType.List
                    ShowType.List -> ShowType.Grid
                }
                onTypeChange(type)
            }),
        contentAlignment = Alignment.Center
    ) {
        when (showType) {
            ShowType.Grid -> {
                Icon(
                    imageVector = Icons.Filled.GridOn,
                    contentDescription = "GridOn",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            ShowType.List -> {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "List",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun CircleTabBar(
    pageCount: Int,
    selectedPage: Int,
    onPageSelected: (page: Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(start = 8.dp, end = 16.dp)
    ) {
        items(pageCount) { index ->
            CircleTab(
                number = index + 1,
                isSelected = selectedPage == index+1,
                onClick = { onPageSelected(index+1) }
            )
        }
    }
}

@Composable
fun CircleTab(
    number: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val size = 50.dp

    val modifier = if (isSelected) {
        Modifier
            .size(size)
            .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
            .clip(CircleShape)
            .clickable(onClick = onClick)
    } else {
        Modifier
            .size(size)
            .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
            .clip(CircleShape)
            .clickable(onClick = onClick)
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            color = if (isSelected)
                MaterialTheme.colorScheme.onPrimary
            else
                MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium
        )
    }
}
package com.example.pokeapp.ui.screen.digimonDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pokeapp.ui.screen.digimonDetail.view.*
import com.example.pokeapp.ui.theme.PinkF
import com.example.pokeapp.ui.view.AppTopBar
import com.example.pokeapp.ui.view.ErrorView
import com.example.pokeapp.ui.view.LoadingView

@Composable
fun DigimonDetailScreen(navController: NavHostController, digimonId: Int, digimonName: String, viewModel: DigimonDetailViewModal = viewModel()) {

    val digimonModel by viewModel.digimonModel.collectAsState()

    val scrollState = rememberScrollState()

    LaunchedEffect(digimonId) {
        viewModel.handleIntent(DigimonDetailIntent.InitDetailPage(digimonId))
    }

    Scaffold(
        topBar = {
            AppTopBar(
                navController = navController,
                title = digimonName,
            )
        }
    ) { padding ->
        if (digimonModel.isApiError) {
            ErrorView()
        } else if (digimonModel.name.isEmpty()) {
            LoadingView()
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                DigimonImageView(digimonId, digimonModel.imageUrl, digimonModel.name)
                TypeAttributeView(digimonModel.types, digimonModel.attributes)
                DigimonDescriptionView(
                    digimonModel.descriptionEN,
                    MaterialTheme.colorScheme.tertiary,
                    MaterialTheme.colorScheme.onTertiary
                )
                DigimonDescriptionView(
                    digimonModel.descriptionJP,
                    PinkF,
                    MaterialTheme.colorScheme.onTertiary
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
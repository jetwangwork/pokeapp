package com.example.pokeapp.ui.screen.pokemonDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pokeapp.ui.screen.pokemonDetail.view.PokemonImageView
import com.example.pokeapp.ui.screen.pokemonDetail.view.PokemonStatBarChart
import com.example.pokeapp.ui.screen.pokemonDetail.view.WeightExpView
import com.example.pokeapp.ui.view.AppTopBar
import com.example.pokeapp.ui.view.ErrorView
import com.example.pokeapp.ui.view.LoadingView

@Composable
fun PokemonDetailScreen(navController: NavHostController, pokemonId: Int, pokemonName: String, viewModel: PokemonDetailViewModal = viewModel()) {

    val pokemonModel by viewModel.pokemonModel.collectAsState()

    val scrollState = rememberScrollState()

    LaunchedEffect(pokemonId) {
        viewModel.handleIntent(PokemonDetailIntent.InitDetailPage(pokemonId))
    }

    Scaffold(
        topBar = {
            AppTopBar(
                navController = navController,
                title = pokemonName
            )
        }
    ) { padding ->
        if (pokemonModel.isApiError) {
            ErrorView()
        } else if (pokemonModel.name.isEmpty()) {
            LoadingView()
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                PokemonImageView(pokemonId, pokemonModel.imageUrl, pokemonModel.name)
                WeightExpView(pokemonModel.weight, pokemonModel.base_experience)
                PokemonStatBarChart(pokemonModel.stats)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
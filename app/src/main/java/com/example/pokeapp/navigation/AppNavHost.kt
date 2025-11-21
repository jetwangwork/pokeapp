package com.example.pokeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokeapp.ui.screen.digimonDetail.DigimonDetailScreen
import com.example.pokeapp.ui.screen.home.HomeScreen
import com.example.pokeapp.ui.screen.pokemonDetail.PokemonDetailScreen

object Routes {
    const val HOME_SCREEN = "HomeScreen"
    const val POKEMON_DETAIL_SCREEN = "PokemonDetailScreen"
    const val DIGIMON_DETAIL_SCREEN = "DigimonDetailScreen"
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.HOME_SCREEN) {
        composable(Routes.HOME_SCREEN) { HomeScreen(navController) }
        composable(
            route = Routes.POKEMON_DETAIL_SCREEN + "/{pokemonId}/{pokemonName}",
            arguments = listOf(
                navArgument("pokemonId") { type = NavType.IntType },
                navArgument("pokemonName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getInt("pokemonId") ?: 0
            val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: ""
            PokemonDetailScreen(navController, pokemonId, pokemonName)
        }
        composable(
            route = Routes.DIGIMON_DETAIL_SCREEN + "/{digimonId}/{digimonName}",
            arguments = listOf(
                navArgument("digimonId") { type = NavType.IntType },
                navArgument("digimonName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val digimonId = backStackEntry.arguments?.getInt("digimonId") ?: 0
            val digimonName = backStackEntry.arguments?.getString("digimonName") ?: ""
            DigimonDetailScreen(navController, digimonId, digimonName)
        }
    }
}
package com.example.pokeapp.ui.screen.pokemonDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.api.response.PokemonDetailResponse
import com.example.pokeapp.api.response.PokemonDetailResponse.Ability
import com.example.pokeapp.api.response.PokemonDetailResponse.Stat
import com.example.pokeapp.api.response.PokemonDetailResponse.Type
import com.example.pokeapp.repository.MonsterRepository
import com.example.pokeapp.util.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class PokemonDetailIntent {
    data class InitDetailPage(val id: Int) : PokemonDetailIntent()
}

class PokemonDetailViewModal(
    private val repository: MonsterRepository = MonsterRepository()
) : ViewModel() {

    private val _pokemonModel = MutableStateFlow<PokemonModel>(PokemonModel.getEmptyModel(false))
    val pokemonModel: StateFlow<PokemonModel> = _pokemonModel

    fun handleIntent(intent: PokemonDetailIntent) {
        viewModelScope.launch {
            when (intent) {
                is PokemonDetailIntent.InitDetailPage -> {
                    getPokemonDetail(intent.id)
                }
            }
        }
    }

    private suspend fun getPokemonDetail(id: Int) {
        try {
            val response = repository.getPokemonDetail(id)
            handlePokemonDetail(response)
        } catch (_: Exception) {
            _pokemonModel.value = PokemonModel.getEmptyModel(true)
        }
    }

    private fun handlePokemonDetail(response: PokemonDetailResponse) {
        _pokemonModel.value = PokemonModel(
            false,
            response.id,
            response.name,
            Utils.getPokemonImageUrl(response.id),
            response.weight,
            response.base_experience,
            response.abilities,
            response.stats,
            response.types
        )
    }
}

data class PokemonModel(
    val isApiError: Boolean,
    val id: Int,
    val name: String,
    val imageUrl: String,
    val weight: Int,
    val base_experience: Int,
    val abilities: List<Ability>,
    val stats: List<Stat>,
    val types: List<Type>,
) {
    companion object {
        fun getEmptyModel(isApiError: Boolean): PokemonModel {
            return PokemonModel(
                isApiError,
                0,
                "",
                "",
                0,
                0,
                emptyList(),
                emptyList(),
                emptyList()
            )
        }
    }
}
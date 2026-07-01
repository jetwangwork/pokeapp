package com.example.pokeapp.repository

import com.example.pokeapp.api.DigimonApiService
import com.example.pokeapp.api.PokemonApiService
import com.example.pokeapp.api.response.DigimonDetailResponse
import com.example.pokeapp.api.response.DigimonListResponse
import com.example.pokeapp.api.response.PokemonDetailResponse
import com.example.pokeapp.api.response.PokemonListResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonsterRepository @Inject constructor(
    private val pokemonApi: PokemonApiService,
    private val digimonApi: DigimonApiService
) {
    suspend fun getPokemons(offset: Int, limit: Int = 20): PokemonListResponse {
        return pokemonApi.getPokemons(offset, limit)
    }

    suspend fun getPokemonDetail(id: Int): PokemonDetailResponse {
        return pokemonApi.getPokemonDetail(id)
    }

    suspend fun getDigimons(page: Int): DigimonListResponse {
        return digimonApi.getDigimons(page)
    }

    suspend fun getDigimonDetail(id: Int): DigimonDetailResponse {
        return digimonApi.getDigimonDetail(id)
    }
}
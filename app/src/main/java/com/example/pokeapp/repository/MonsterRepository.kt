package com.example.pokeapp.repository

import com.example.pokeapp.api.ApiManager
import com.example.pokeapp.api.response.DigimonDetailResponse
import com.example.pokeapp.api.response.DigimonListResponse
import com.example.pokeapp.api.response.PokemonDetailResponse
import com.example.pokeapp.api.response.PokemonListResponse

class MonsterRepository {
    suspend fun getPokemons(offset: Int, limit: Int = 20): PokemonListResponse {
        return ApiManager.pokemonApi.getPokemons(offset, limit)
    }

    suspend fun getPokemonDetail(id: Int): PokemonDetailResponse {
        return ApiManager.pokemonApi.getPokemonDetail(id)
    }

    suspend fun getDigimons(page: Int): DigimonListResponse {
        return ApiManager.digimonApi.getDigimons(page)
    }

    suspend fun getDigimonDetail(id: Int): DigimonDetailResponse {
        return ApiManager.digimonApi.getDigimonDetail(id)
    }
}
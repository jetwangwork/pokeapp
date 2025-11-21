package com.example.pokeapp.api

import com.example.pokeapp.api.response.DigimonDetailResponse
import com.example.pokeapp.api.response.DigimonListResponse
import com.example.pokeapp.api.response.PokemonDetailResponse
import com.example.pokeapp.api.response.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonListResponse

    @GET("pokemon/{id}/")
    suspend fun getPokemonDetail(@Path("id") id: Int): PokemonDetailResponse
}

interface DigimonApiService {
    @GET("digimon")
    suspend fun getDigimons(@Query("page") page: Int): DigimonListResponse

    @GET("digimon/{id}/")
    suspend fun getDigimonDetail(@Path("id") id: Int): DigimonDetailResponse
}
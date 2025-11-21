package com.example.pokeapp.api.response

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonItem>
) {
    @Serializable
    data class PokemonItem(
        val name: String,
        val url: String
    )
}
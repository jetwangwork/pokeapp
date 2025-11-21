package com.example.pokeapp.api.response

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    val abilities: List<Ability>,
    val base_experience: Int,
    val id: Int,
    val name: String,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
) {
    @Serializable
    data class Ability(
        val ability: AbilityX,
        val is_hidden: Boolean,
        val slot: Int
    ) {
        @Serializable
        data class AbilityX(
            val name: String,
            val url: String
        )
    }

    @Serializable
    data class Stat(
        val base_stat: Int,
        val effort: Int,
        val stat: StatX
    ) {
        @Serializable
        data class StatX(
            val name: String,
            val url: String
        )
    }

    @Serializable
    data class Type(
        val slot: Int,
        val type: TypeX
    ) {
        @Serializable
        data class TypeX(
            val name: String,
            val url: String
        )
    }
}
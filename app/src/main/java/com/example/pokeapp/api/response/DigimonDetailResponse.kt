package com.example.pokeapp.api.response

import kotlinx.serialization.Serializable

@Serializable
data class DigimonDetailResponse(
    val attributes: List<Attribute>,
    val descriptions: List<Description>,
    val fields: List<Field>,
    val id: Int,
    val images: List<Image>,
    val levels: List<Level>,
    val name: String,
    val nextEvolutions: List<NextEvolution>,
    val priorEvolutions: List<PriorEvolution>,
    val releaseDate: String,
    val skills: List<Skill>,
    val types: List<Type>,
    val xAntibody: Boolean
) {
    @Serializable
    data class Attribute(
        val attribute: String,
        val id: Int
    )

    @Serializable
    data class Description(
        val description: String,
        val language: String,
        val origin: String
    )

    @Serializable
    data class Field(
        val `field`: String,
        val id: Int,
        val image: String
    )

    @Serializable
    data class Image(
        val href: String,
        val transparent: Boolean
    )

    @Serializable
    data class Level(
        val id: Int,
        val level: String
    )

    @Serializable
    data class NextEvolution(
        val condition: String,
        val digimon: String,
        val id: Int,
        val image: String,
        val url: String
    )

    @Serializable
    data class PriorEvolution(
        val condition: String,
        val digimon: String,
        val id: Int,
        val image: String,
        val url: String
    )

    @Serializable
    data class Skill(
        val description: String,
        val id: Int,
        val skill: String,
        val translation: String
    )

    @Serializable
    data class Type(
        val id: Int,
        val type: String
    )
}
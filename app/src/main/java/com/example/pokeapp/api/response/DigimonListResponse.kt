package com.example.pokeapp.api.response

import kotlinx.serialization.Serializable

@Serializable
data class DigimonListResponse(
    val pageable: Pageable,
    val content: List<DigimonItem>
) {
    @Serializable
    data class Pageable(
        val currentPage: Int,
        val elementsOnPage: Int,
        val nextPage: String,
        val previousPage: String,
        val totalElements: Int,
        val totalPages: Int
    )

    @Serializable
    data class DigimonItem(
        val id: Int,
        val name: String,
        val href: String,
        val image: String
    )
}
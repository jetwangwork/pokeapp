package com.example.pokeapp.util

object Utils {
    fun getPokemonImageUrl(id: Int): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    }
}
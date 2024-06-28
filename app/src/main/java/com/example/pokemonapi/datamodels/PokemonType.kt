package com.example.pokemonapi.datamodels

data class PokemonType(
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String,
    val url: String
)
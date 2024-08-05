package com.example.pokemonapi.list.data.internal

data class PokemonGeneration(
    val id: Int,
    val name: String,
    val pokemon_species: List<PokemonSpecies>
)

data class PokemonSpecies(
    val name: String,
    val url: String
)
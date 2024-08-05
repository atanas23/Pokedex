package com.example.pokemonapi.list.data.external

data class PokemonGenerationExternal(
    val id: Int,
    val name: String,
    val pokemon_species: List<PokemonSpeciesExternal>
)

data class PokemonSpeciesExternal(
    val name: String,
    val url: String
)
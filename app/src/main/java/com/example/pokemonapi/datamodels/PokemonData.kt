package com.example.pokemonapi.datamodels

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonType>,
    val sprites: Sprites,
    val abilities: List<PokemonAbility>,
    val stats: List<PokemonStat>
)

data class Sprites(
    val front_default: String
)
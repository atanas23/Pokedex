package com.example.pokemonapi.datamodels

data class PokemonAbility(
    val ability: Ability,
    val is_hidden: Boolean,
    val slot: Int
)

data class Ability(
    val name: String,
    val url: String
)
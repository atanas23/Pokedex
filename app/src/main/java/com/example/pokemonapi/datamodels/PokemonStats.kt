package com.example.pokemonapi.datamodels

data class PokemonStat(
    val base_stat: Int,
    val stat: Stat
)

data class Stat (
    val name: String,
    val url: String
)
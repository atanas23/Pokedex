package com.example.pokemonapi.details.data.internal

import com.example.pokemonapi.datamodels.PokemonAbility
import com.example.pokemonapi.datamodels.PokemonStat
import com.example.pokemonapi.datamodels.PokemonType

data class PokemonData(
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
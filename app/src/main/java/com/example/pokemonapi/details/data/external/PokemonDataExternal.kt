package com.example.pokemonapi.details.data.external

import com.example.pokemonapi.datamodels.PokemonAbility
import com.example.pokemonapi.datamodels.PokemonStat
import com.example.pokemonapi.datamodels.PokemonType

data class PokemonDataExternal(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonType>,
    val sprites: SpritesExternal,
    val abilities: List<PokemonAbility>,
    val stats: List<PokemonStat>
)

data class SpritesExternal(
    val front_default: String
)
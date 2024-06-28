package com.example.pokemonapi.common

import com.example.pokemonapi.R

fun getDrawableResource(typeName: String): Int {
    return when (typeName) {
        "normal" -> R.drawable.normal
        "poison" -> R.drawable.poison
        "psychic" -> R.drawable.psychic
        "grass" -> R.drawable.grass
        "ground" -> R.drawable.ground
        "ice" -> R.drawable.ice
        "fire" -> R.drawable.fire
        "rock" -> R.drawable.rock
        "dragon" -> R.drawable.dragon
        "water" -> R.drawable.water
        "bug" -> R.drawable.bug
        "dark" -> R.drawable.dark
        "fighting" -> R.drawable.fighting
        "ghost" -> R.drawable.ghost
        "steel" -> R.drawable.steel
        "flying" -> R.drawable.flying
        "electric" -> R.drawable.electric
        "fairy" -> R.drawable.fairy
        else -> R.drawable.unknown
    }
}
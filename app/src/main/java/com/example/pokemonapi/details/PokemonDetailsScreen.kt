package com.example.pokemonapi.details

import androidx.compose.runtime.Composable
import com.example.pokemonapi.details.data.internal.PokemonData

@Composable
fun PokemonFullDetailsScreen(pokemon: PokemonData) {
    PokemonDetailedItem(pokemon = pokemon)
}
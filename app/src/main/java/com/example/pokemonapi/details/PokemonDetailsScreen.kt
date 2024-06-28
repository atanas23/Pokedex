package com.example.pokemonapi.details

import androidx.compose.runtime.Composable
import com.example.pokemonapi.datamodels.Pokemon

@Composable
fun PokemonFullDetailsScreen(pokemon: Pokemon) {
    PokemonDetailedItem(pokemon = pokemon)
}
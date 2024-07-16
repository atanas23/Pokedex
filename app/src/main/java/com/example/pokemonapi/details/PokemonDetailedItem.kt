package com.example.pokemonapi.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokemonapi.common.PokemonImage
import com.example.pokemonapi.common.getBackgroundColor
import com.example.pokemonapi.details.data.internal.PokemonData
@Composable
fun PokemonDetailedItem(pokemon: PokemonData) {
    val backgroundColor = getBackgroundColor(pokemon.types.firstOrNull()?.type?.name)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp
            ),

    ) {
        Row {
            PokemonImage(pokemon.sprites.front_default, 200.dp)
            Spacer(modifier = Modifier.weight(1f))
            PokemonFullDetails(pokemon = pokemon)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            PokemonAbilities(abilities = pokemon.abilities, backgroundColor)
        }
        Row{
            PokemonStatsChart(stats = pokemon.stats, backgroundColor)
        }
    }
}
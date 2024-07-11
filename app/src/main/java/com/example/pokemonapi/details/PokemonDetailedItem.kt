package com.example.pokemonapi.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokemonapi.common.PokemonImage
import com.example.pokemonapi.common.getBackgroundColor
import com.example.pokemonapi.datamodels.Pokemon
import com.example.pokemonapi.datamodels.PokemonStat

@Composable
fun PokemonDetailedItem(pokemon: Pokemon) {
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
        Row {
            //            PokemonStats(stats = pokemon.stats)
            PokemonStatsChart(stats = pokemon.stats)
        }
    }
}
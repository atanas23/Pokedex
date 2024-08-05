package com.example.pokemonapi.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokemonapi.PokemonDetailsViewModel
import com.example.pokemonapi.common.PokemonImage
import com.example.pokemonapi.common.getBackgroundColor
import com.example.pokemonapi.details.data.internal.PokemonData

@Composable
fun PokemonFullDetailsScreen(
    detailsViewModel: PokemonDetailsViewModel,
    pokemonName: String
) {
    val pokemon = detailsViewModel.pokemonDetails.observeAsState()
    detailsViewModel.getPokemonDetails(pokemonName)

    val backgroundColor = getBackgroundColor(pokemon.value?.types?.firstOrNull()?.type?.name)
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
            pokemon.value?.sprites?.front_default?.let { PokemonImage(it, 200.dp) }
            Spacer(modifier = Modifier.weight(1f))
            pokemon.value?.let { PokemonFullDetails(pokemon = it) }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            pokemon.value?.abilities?.let { PokemonAbilities(abilities = it, backgroundColor) }
        }
        Row{
            pokemon.value?.stats?.let { PokemonStatsChart(stats = it, backgroundColor) }
        }
    }
}
package com.example.pokemonapi.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.pokemonapi.datamodels.PokemonStat

@Composable
fun PokemonStats(stats: List<PokemonStat>) {
    stats.forEach { stat ->
        Text(text = "${stat.stat.name}: ${stat.base_stat}")
    }
}
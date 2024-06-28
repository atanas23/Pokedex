package com.example.pokemonapi.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pokemonapi.common.TypeRow
import com.example.pokemonapi.datamodels.Pokemon
import com.example.pokemonapi.utils.heightToCm
import com.example.pokemonapi.utils.weightToKg

@Composable
fun PokemonFullDetails(pokemon: Pokemon) {
    val pokemonWeight = weightToKg(pokemon.weight)
    val pokemonHeight = heightToCm(pokemon.height)

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = pokemon.name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.headlineMedium
        )
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            AssistChip(
                onClick = { /*TODO*/ },
                label = { Text(text = "$pokemonHeight cm") },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = Color.White
                )
            )
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            AssistChip(
                onClick = { /*TODO*/ },
                label = { Text(text = "$pokemonWeight kg") },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = Color.White
                )
            )
        }
        TypeRow(pokemon.types)
    }
}
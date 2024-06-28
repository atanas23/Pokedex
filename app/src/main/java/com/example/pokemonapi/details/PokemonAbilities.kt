package com.example.pokemonapi.details

import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.pokemonapi.datamodels.PokemonAbility

@Composable
fun PokemonAbilities(abilities: List<PokemonAbility>) {
    abilities.forEach { ability ->
        AssistChip(
            onClick = {},
            label = { Text(text = ability.ability.name) },
            colors = AssistChipDefaults.assistChipColors(
                containerColor = Color.White
            )
        )
    }
}
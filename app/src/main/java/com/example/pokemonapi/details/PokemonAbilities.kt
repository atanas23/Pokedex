package com.example.pokemonapi.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pokemonapi.datamodels.PokemonAbility

@Composable
fun PokemonAbilities(abilities: List<PokemonAbility>, backgroundColor: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Abilities",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            abilities.forEach { ability ->
                AbilityItem(ability = ability, backgroundColor)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun AbilityItem(ability: PokemonAbility, backgroundColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (ability.is_hidden) {
            Text(
                text = "${ability.ability.name.replaceFirstChar { it.uppercase() }} (hidden)",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        } else {
            Text(
                text = ability.ability.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
package com.example.pokemonapi.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pokemonapi.common.PokemonID
import com.example.pokemonapi.common.PokemonImage
import com.example.pokemonapi.common.getBackgroundColor
import com.example.pokemonapi.details.data.internal.PokemonData

@SuppressLint("InvalidColorHexValue")
@Composable
fun PokemonItem(pokemon: PokemonData, navController: NavHostController) {
    val backgroundColor = getBackgroundColor(pokemon.types.firstOrNull()?.type?.name)

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                navController.navigate("pokemon_details/${pokemon.id}")
                Log.d("pokemon ID: ", "pokemon_details/${pokemon.id}")
            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(4.dp)
                .background(color = backgroundColor, shape = MaterialTheme.shapes.medium)
                .padding(4.dp),
            verticalAlignment = Alignment.Top
        ) {
            PokemonID(pokemon.id)
            PokemonListDetails(pokemon)
            Spacer(modifier = Modifier.weight(1f))
            PokemonImage(pokemon.sprites.front_default, 120.dp)
        }
    }
}
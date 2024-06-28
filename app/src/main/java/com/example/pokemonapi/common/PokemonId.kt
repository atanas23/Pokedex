package com.example.pokemonapi.common

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PokemonID(id: Int) {
    Text(
        text = "#${id.toString().padStart(3, '0')}",
        modifier = Modifier
            .wrapContentHeight(Alignment.Top)
            .width(50.dp)
    )
}
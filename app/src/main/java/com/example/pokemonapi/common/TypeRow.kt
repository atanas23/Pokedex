package com.example.pokemonapi.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokemonapi.datamodels.PokemonType

@Composable
fun TypeRow(types: List<PokemonType>) {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        types.forEachIndexed { index, type ->
            if (index > 0){
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            }
            val pokemonType = type.type.name.uppercase()
            val drawableResId = getDrawableResource(type.type.name)
            AssistChip(
                onClick = { Log.d("assist chip", "test")},
                label = { Text(text = type.type.name.uppercase()) },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = drawableResId),
                        contentDescription = pokemonType,
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = Color.White
                )
            )
        }
    }
}
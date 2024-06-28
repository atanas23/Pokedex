package com.example.pokemonapi.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonImage(imageUrl: String, size: Dp) {
    GlideImage(
        model = imageUrl,
        contentDescription = "Pokemon Image",
        modifier = Modifier
            .size(size)
            .padding(2.dp),
        contentScale = ContentScale.Crop
    )
}
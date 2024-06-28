package com.example.pokemonapi.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getBackgroundColor(typeName: String?): Color {
    return when (typeName) {
        "normal" -> Color(0xFFA8A878)
        "poison" -> Color(0xFFA040A0)
        "psychic" -> Color(0xFFF85888)
        "grass" -> Color(0xFF78C850)
        "ground" -> Color(0xFFE0C068)
        "ice" -> Color(0xFF98D8D8)
        "fire" -> Color(0xFFF08030)
        "rock" -> Color(0xFFB8A038)
        "dragon" -> Color(0xFF7038F8)
        "water" -> Color(0xFF6890F0)
        "bug" -> Color(0xFFA8B820)
        "dark" -> Color(0xFF705848)
        "fighting" -> Color(0xFFC03028)
        "ghost" -> Color(0xFF705898)
        "steel" -> Color(0xFFB8B8D0)
        "flying" -> Color(0xFFA890F0)
        "electric" -> Color(0xFFF8D030)
        "fairy" -> Color(0xFFEE99AC)
        else -> Color(0xFFD3D3D3)
    }
}
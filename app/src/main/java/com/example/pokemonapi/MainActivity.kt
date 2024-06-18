package com.example.pokemonapi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

class MainActivity : ComponentActivity() {
    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pokemonDataList by viewModel.pokemonDataList.observeAsState(emptyList())
            val errorMessage by viewModel.errorMessage.observeAsState()

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                PokemonScreen(
                    pokemons = pokemonDataList,
                    errorMessage = errorMessage,
                    onFetchPokemon = {
                        viewModel.getPokemon(1)
                    }
                )
            }
        }

        viewModel.getPokemon(1)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@SuppressLint("InvalidColorHexValue")
@Composable
fun PokemonItem(pokemon: Pokemon) {
    val backgroundColor = when (pokemon.types.firstOrNull()?.type?.name) {
        "normal" -> Color(0xFFA8A878)
        "poison" -> Color(0xFFFA040A0)
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(4.dp)
            .background(color = backgroundColor, shape = MaterialTheme.shapes.medium)
            .padding(4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "#${pokemon.id.toString().padStart(3, '0')}",
            modifier = Modifier
                .wrapContentHeight(Alignment.Top)
                .width(50.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                pokemon.types.forEach { type ->
                    Box(
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .border(1.dp, Color.DarkGray, shape = MaterialTheme.shapes.small)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = type.type.name.uppercase(),
                        )
                    }
                }
            }
        }

        GlideImage(
            model = pokemon.sprites.front_default,
            contentDescription = "Pokemon Image",
            modifier = Modifier
                .size(120.dp)
                .padding(2.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun PokemonScreen(
    pokemons: List<Pokemon>,
    errorMessage: String?,
    onFetchPokemon: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(pokemons) { pokemon ->
            PokemonItem(pokemon = pokemon)
        }
        item {
            errorMessage?.let {
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

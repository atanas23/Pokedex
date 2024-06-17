package com.example.pokemonapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

class MainActivity : ComponentActivity() {
    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pokemonDataList by viewModel.pokemonDataList.observeAsState()
            val errorMessage by viewModel.errorMessage.observeAsState()

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                PokemonScreen(
                    pokemons = pokemonDataList,
                    errorMessage = errorMessage,
                    onFetchPokemon = {
                        viewModel.getPokemon(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))
                    }
                )
            }
        }

        viewModel.getPokemon(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonScreen(
    pokemons: List<Pokemon>?,
    errorMessage: String?,
    onFetchPokemon: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                start = 2.dp,
                bottom = 4.dp
            ), //padding start, top
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        pokemons?.let {
            items(pokemons) { pokemon ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GlideImage(
                        model = pokemon.sprites.front_default,
                        contentDescription = "Pokemon Image",
                        modifier = Modifier
                            .size(180.dp)
                            .padding(16.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(
                                    start = 16.dp,
                                    bottom = 8.dp
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = pokemon.name.replaceFirstChar { char -> char.titlecaseChar() })
                        }
                        Column(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "height: ${pokemon.height * 10} cm.")
                            Text(text = "weight: ${pokemon.weight / 10} kg.")
                            Row {
                                Text(text = "types: ")
                                pokemon.types.forEach { type -> Text(text = "${type.type.name} ") }
                            }
                        }
                    }
                }
            }
        }
        item {
            errorMessage?.let {
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokemonScreen(
        pokemons = null,
        errorMessage = null,
        onFetchPokemon = {}
    )
}
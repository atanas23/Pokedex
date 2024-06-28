package com.example.pokemonapi.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pokemonapi.datamodels.Pokemon

@Composable
fun PokemonListScreen(
    pokemons: List<Pokemon>,
    errorMessage: String?,
    onFetchPokemon: () -> Unit,
    navController: NavHostController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(pokemons) { pokemon ->
            PokemonItem(pokemon = pokemon, navController = navController) // Pass NavController to PokemonItem
        }
        item {
            errorMessage?.let {
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
package com.example.pokemonapi.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pokemonapi.PokemonListViewModel

@Composable
fun PokemonListScreen(
    listViewModel: PokemonListViewModel,
    navController: NavHostController
) {
    val pokemonList = listViewModel.pokemonList.observeAsState()
    listViewModel.getPokemonList(1)
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        pokemonList.value?.pokemon_species?.let {
            items(it) { pokemon ->
                PokemonItem(pokemon = pokemon, navController = navController)
            }
        }
        item {
            Text(text = "something went wrong", color = MaterialTheme.colorScheme.error)
        }
    }
}
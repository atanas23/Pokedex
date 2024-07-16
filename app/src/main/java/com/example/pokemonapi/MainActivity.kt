package com.example.pokemonapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonapi.details.PokemonFullDetailsScreen
import com.example.pokemonapi.details.PokemonRemoteAdapter
import com.example.pokemonapi.details.PokemonRemoteDataSource
import com.example.pokemonapi.details.PokemonRepository
import com.example.pokemonapi.details.data.internal.PokemonData
import com.example.pokemonapi.list.PokemonListScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val adapter = PokemonRemoteAdapter()
            val api = RetrofitInstance.api
            val dataSource = PokemonRemoteDataSource(api, adapter)
            val repository = PokemonRepository(dataSource)
            val viewModel: PokemonViewModel = viewModel(
                factory = PokemonViewModelFactory(repository)
            )

            val pokemonDataList by viewModel.pokemonDataList.observeAsState(emptyList())
            val errorMessage by viewModel.errorMessage.observeAsState()

            viewModel.getPokemon(1)

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                NavHost(navController = navController, startDestination = "pokemon_list") {
                    composable("pokemon_list") {
                        PokemonListScreen(
                            pokemons = pokemonDataList,
                            errorMessage = errorMessage,
                            onFetchPokemon = {
                                viewModel.getPokemon(1)
                            },
                            navController = navController
                        )
                    }

                    composable("pokemon_details/{pokemonId}") { backStackEntry ->
                        val pokemonId = backStackEntry.arguments?.getString("pokemonId")
                        val pokemon = pokemonDataList.find { it.id == pokemonId?.toInt() }
                        pokemon?.let {
                            PokemonFullDetailsScreen(pokemon = it)
                        }
                    }
                }
            }
        }
    }
}
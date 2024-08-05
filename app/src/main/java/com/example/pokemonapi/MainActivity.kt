package com.example.pokemonapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonapi.details.PokemonDetailsRemoteAdapter
import com.example.pokemonapi.details.PokemonDetailsRemoteDataSource
import com.example.pokemonapi.details.PokemonDetailsRepository
import com.example.pokemonapi.details.PokemonFullDetailsScreen
import com.example.pokemonapi.list.PokemonListRemoteAdapter
import com.example.pokemonapi.list.PokemonListRemoteDataSource
import com.example.pokemonapi.list.PokemonListRepository
import com.example.pokemonapi.list.PokemonListScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val api = RetrofitInstance.api

            val detailsAdapter = PokemonDetailsRemoteAdapter()
            val listAdapter = PokemonListRemoteAdapter()

            val detailsDataSource = PokemonDetailsRemoteDataSource(api, detailsAdapter)
            val listsDataSource = PokemonListRemoteDataSource(api, listAdapter)

            val detailsRepository = PokemonDetailsRepository(detailsDataSource)
            val listRepository = PokemonListRepository(listsDataSource)

            val detailsViewModel: PokemonDetailsViewModel = viewModel(
                factory = PokemonDetailsViewModelFactory(detailsRepository)
            )
            val listViewModel: PokemonListViewModel = viewModel(
                factory = PokemonListViewModelFactory(listRepository)
            )

            listViewModel.getPokemonList(1)

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                NavHost(navController = navController, startDestination = "pokemon_list") {
                    composable("pokemon_list") {
                        PokemonListScreen(
                            listViewModel = listViewModel,
                            navController = navController
                        )
                    }

                    composable("pokemon_details/{name}") { backStackEntry ->
                        val pokemonName = backStackEntry.arguments?.getString("name")

                        if (pokemonName != null) {
                            PokemonFullDetailsScreen(
                                detailsViewModel = detailsViewModel,
                                pokemonName = pokemonName
                            )
                        }
                    }
                }
            }
        }
    }
}
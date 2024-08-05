package com.example.pokemonapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonapi.details.PokemonDetailsRepository

class PokemonDetailsViewModelFactory(private val repository: PokemonDetailsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PokemonDetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PokemonDetailsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

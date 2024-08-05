package com.example.pokemonapi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapi.details.PokemonDetailsRepository
import com.example.pokemonapi.details.data.internal.PokemonData
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(val repository: PokemonDetailsRepository) : ViewModel() {
    val pokemonDetails = MutableLiveData<PokemonData>()
    val errorMessage = MutableLiveData<String>()

    fun getPokemonDetails(pokemonName: String) {
        viewModelScope.launch {
            try {
                val details = repository.getPokemonDetails(pokemonName)
                pokemonDetails.postValue(details)
            } catch (e: Exception) {
                errorMessage.postValue("Error: ${e.message}")
                Log.e("PokemonViewModel", "Error fetching Pokémon data", e)
            }
        }
    }
}

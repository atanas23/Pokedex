package com.example.pokemonapi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapi.list.PokemonListRepository
import com.example.pokemonapi.list.data.internal.PokemonGeneration
import kotlinx.coroutines.launch

class PokemonListViewModel(val repository: PokemonListRepository) : ViewModel() {

    val pokemonList = MutableLiveData<PokemonGeneration>()
    val errorMessage = MutableLiveData<String>()

    fun getPokemonList(generation: Int) {
        viewModelScope.launch {
            try {
                val generationData = repository.getPokemonList(generation)
                pokemonList.postValue(generationData)
            } catch (e: Exception) {
                errorMessage.postValue("Error: ${e.message}")
                Log.e("PokemonViewModel", "Error fetching Pokémon data", e)
            }
        }
    }
}
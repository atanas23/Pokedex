package com.example.pokemonapi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapi.datamodels.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

class PokemonViewModel : ViewModel() {

    val pokemonDataList = MutableLiveData<List<Pokemon>>()
    val errorMessage = MutableLiveData<String>()

    fun getPokemon(generation: Int) {
        viewModelScope.launch {
            try {
                val generationData = RetrofitInstance.api.getPokemonByGen(generation)
                val pokemonList = generationData.pokemon_species.map { species ->
                    async(Dispatchers.IO) {
                        RetrofitInstance.api.getPokemonDetails(species.name)
                    }
                }.awaitAll().sortedBy { it.id }
                pokemonDataList.postValue(pokemonList)
            } catch (e: Exception) {
                errorMessage.postValue("Error: ${e.message}")
                Log.e("PokemonViewModel", "Error fetching Pokémon data", e)
            }
        }
    }
}

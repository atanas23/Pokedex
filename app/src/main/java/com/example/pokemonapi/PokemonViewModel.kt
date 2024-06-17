package com.example.pokemonapi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

class PokemonViewModel : ViewModel() {

    val pokemonDataList = MutableLiveData<List<Pokemon>>()
    val errorMessage = MutableLiveData<String>()

    fun getPokemon(numbs: List<Int>) {
        viewModelScope.launch {
            try {
                val pokemons = numbs.map { numb ->
                    async { RetrofitInstance.api.getPokemonByNumb(numb) }
                }.awaitAll()
                pokemonDataList.postValue(pokemons)
                Log.d("PokemonViewModel", "Pokémon data: $pokemons")
            } catch (e: Exception) {
                errorMessage.postValue("Error: ${e.message}")
                Log.e("PokemonViewModel", "Error fetching Pokémon data", e)
            }
        }
    }
}

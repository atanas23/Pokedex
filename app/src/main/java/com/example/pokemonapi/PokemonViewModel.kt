package com.example.pokemonapi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapi.details.PokemonRepository
import com.example.pokemonapi.details.data.internal.PokemonData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

//TODO: each screen needs its own viewModel after I remove chips from ListScreen
class PokemonViewModel(val repository: PokemonRepository) : ViewModel() {

    val pokemonDataList = MutableLiveData<List<PokemonData>>()
    val errorMessage = MutableLiveData<String>()

    fun getPokemon(generation: Int) {
        viewModelScope.launch {
            try {
                //TODO: too many server hits, should remove the type chips to be more effective
                val generationData = RetrofitInstance.api.getPokemonByGen(generation)
                val pokemonList = generationData.pokemon_species.map { species ->
                    async(Dispatchers.IO) {
                        repository.getPokemonDetails(species.name)
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

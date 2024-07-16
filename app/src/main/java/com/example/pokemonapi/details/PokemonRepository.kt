package com.example.pokemonapi.details

import com.example.pokemonapi.details.data.internal.PokemonData

class PokemonRepository(val dataSource: PokemonRemoteDataSource) { //val = this.dataSource = dataSource //this for class, it for object
    //add cash for pokemon details
    suspend fun getPokemonDetails(pokemonName: String): PokemonData {
       return dataSource.getPokemonDetails(pokemonName)
    }
}
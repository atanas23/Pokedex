package com.example.pokemonapi.details

import com.example.pokemonapi.details.data.internal.PokemonData

class PokemonDetailsRepository(val dataSource: PokemonDetailsRemoteDataSource) { //val = this.dataSource = dataSource //this for class, it for object
    //add cache for pokemon details
    suspend fun getPokemonDetails(pokemonName: String): PokemonData {
       return dataSource.getPokemonDetails(pokemonName)
    }
}
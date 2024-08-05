package com.example.pokemonapi.list

import com.example.pokemonapi.list.data.internal.PokemonGeneration

class PokemonListRepository(val dataSource: PokemonListRemoteDataSource) {
    suspend fun getPokemonList(generation: Int): PokemonGeneration {
        return dataSource.getPokemonList(generation)
    }
}
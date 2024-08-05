package com.example.pokemonapi.list

import com.example.pokemonapi.PokemonApi
import com.example.pokemonapi.list.data.external.PokemonGenerationExternal
import com.example.pokemonapi.list.data.internal.PokemonGeneration

class PokemonListRemoteDataSource(val pokemonApi: PokemonApi, val adapter: PokemonListRemoteAdapter) {
    suspend fun getPokemonList(generation: Int): PokemonGeneration {
        val pokemonList = pokemonApi.getPokemonByGen(generation)
        return adapter.adaptPokemonListExternalToInternal(pokemonList)
    }
}
package com.example.pokemonapi.details

import com.example.pokemonapi.PokemonApi
import com.example.pokemonapi.details.data.internal.PokemonData

class PokemonRemoteDataSource(val pokemonApi: PokemonApi, val adapter: PokemonRemoteAdapter) {
    suspend fun getPokemonDetails(pokemonName: String): PokemonData {
        val pokemonDetails = pokemonApi.getPokemonDetails(pokemonName)
        return adapter.adaptPokemonListExternalToInternal(pokemonDetails)
    }
}
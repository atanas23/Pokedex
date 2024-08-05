package com.example.pokemonapi.details

import com.example.pokemonapi.PokemonApi
import com.example.pokemonapi.details.data.internal.PokemonData

class PokemonDetailsRemoteDataSource(val pokemonApi: PokemonApi, val adapter: PokemonDetailsRemoteAdapter) {
    suspend fun getPokemonDetails(pokemonName: String): PokemonData {
        val pokemonDetails = pokemonApi.getPokemonDetails(pokemonName)
        return adapter.adaptPokemonDetailsExternalToInternal(pokemonDetails)
    }
}
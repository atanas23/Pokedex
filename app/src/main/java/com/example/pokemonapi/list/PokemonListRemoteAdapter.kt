package com.example.pokemonapi.list

import com.example.pokemonapi.list.data.external.PokemonGenerationExternal
import com.example.pokemonapi.list.data.internal.PokemonGeneration
import com.example.pokemonapi.list.data.internal.PokemonSpecies

class PokemonListRemoteAdapter {
    fun adaptPokemonListExternalToInternal(pokemonGenerationExternal: PokemonGenerationExternal): PokemonGeneration {
        return pokemonGenerationExternal.let {
            PokemonGeneration(
                id = it.id,
                name = it.name,
                pokemon_species = it.pokemon_species.map { externalSPecies ->
                    PokemonSpecies(
                        name = externalSPecies.name,
                        url = externalSPecies.url
                    )
                }
            )
        }
    }
}
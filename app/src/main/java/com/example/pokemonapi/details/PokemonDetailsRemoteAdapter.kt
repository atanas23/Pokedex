package com.example.pokemonapi.details

import com.example.pokemonapi.details.data.external.PokemonDataExternal
import com.example.pokemonapi.details.data.internal.PokemonData
import com.example.pokemonapi.details.data.internal.Sprites

class PokemonDetailsRemoteAdapter {
    fun adaptPokemonDetailsExternalToInternal(pokemonDetails: PokemonDataExternal): PokemonData {
        return pokemonDetails.let {
            PokemonData(
                id = it.id,
                name = it.name,
                height = it.height,
                weight = it.weight,
                abilities = it.abilities,
                stats = it.stats,
                sprites = Sprites(it.sprites.front_default),
                types = it.types,
            )
        }
    }
}
package com.example.pokemonapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonType>,
    val sprites: Sprites
)

data class Generation(
    val id: Int,
    val name: String,
    val pokemon_species: List<PokemonSpecies>
)

data class PokemonSpecies(
    val name: String,
    val url: String
)

data class Sprites(
    val front_default: String
)

data class PokemonType(
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String,
    val url: String
)

interface PokemonApi {
    @GET("generation/{generation}")
    suspend fun getPokemonByGen(@Path("generation") numb: Int): Generation

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String): Pokemon
}

object RetrofitInstance {
    private const val POKEMON_BASE_URL = "https://pokeapi.co/api/v2/"

    val api: PokemonApi by lazy {
        Retrofit.Builder()
            .baseUrl(POKEMON_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }
}

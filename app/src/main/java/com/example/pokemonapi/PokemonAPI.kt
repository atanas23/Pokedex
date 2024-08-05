package com.example.pokemonapi

import com.example.pokemonapi.details.data.external.PokemonDataExternal
import com.example.pokemonapi.list.data.external.PokemonGenerationExternal
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("generation/{generation}")
    suspend fun getPokemonByGen(@Path("generation") numb: Int): PokemonGenerationExternal

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String): PokemonDataExternal
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

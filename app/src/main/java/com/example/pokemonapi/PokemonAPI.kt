package com.example.pokemonapi

import com.example.pokemonapi.datamodels.Generation
import com.example.pokemonapi.datamodels.Pokemon
import com.example.pokemonapi.datamodels.PokemonAbility
import com.example.pokemonapi.datamodels.PokemonStat
import com.example.pokemonapi.datamodels.PokemonType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

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

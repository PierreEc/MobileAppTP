package com.example.myapplication.service

import com.example.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonServiceInterface {
    @GET("pokemon")
    suspend fun fetchPokemons(): List<Pokemon>;

    @GET("pokemon/{id}")
    suspend fun fetchPokemonById(@Path("id") id: Int): Pokemon;
}



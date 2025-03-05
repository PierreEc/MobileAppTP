package com.example.myapplication.service

import com.example.model.Pokemon
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface PokemonServiceInterface {
    @GET("pokemon")
    suspend fun fetchPokemons(): List<Pokemon>;

    @GET("pokemon/{id}")
    suspend fun fetchPokemonById(@Path("id") id: Int): Pokemon;
}



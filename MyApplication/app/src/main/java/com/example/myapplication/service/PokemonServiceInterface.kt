package com.example.myapplication.service

import com.example.model.Pokemon
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface PokemonServiceInterface {
    @GET("pokemon")
    suspend fun fetchPokemons(): List<Pokemon>;
}

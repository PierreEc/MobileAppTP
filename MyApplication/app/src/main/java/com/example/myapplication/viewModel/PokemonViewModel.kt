package com.example.myapplication.viewModel

import PokemonService
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class PokemonViewModel: ViewModel(){
    var pokemon : MutableStateFlow<List<Pokemon>> = MutableStateFlow <List<Pokemon>>(emptyList())
    init{
        getPokemons()
    }

     fun getPokemons() {
        viewModelScope.launch{
            try {
                pokemon.value = PokemonService.retrofit.fetchPokemons()
            }
            catch (
                e: Exception
            ){
                Log.e("PokemonViewModel", e.toString()

                )
            }
        }
    }

    fun getRandomPokemon(): Pokemon {
        val randomIndex = (1 until 1025).random()
    }
}
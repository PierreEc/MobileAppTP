package com.example.myapplication.viewModel

import PokemonService
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.extension.removeAccents
import com.example.model.Pokemon
import com.example.model.QuizState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons: StateFlow<List<Pokemon>> = _pokemons.asStateFlow()

    private val _quizState = MutableStateFlow(QuizState())
    val quizState: StateFlow<QuizState> = _quizState.asStateFlow()

    init {
        getPokemons()
        getRandomPokemon()
    }

    fun getPokemons() {
        viewModelScope.launch {
            try {
                _pokemons.value = PokemonService.retrofit.fetchPokemons().drop(1)
            } catch (e: Exception) {
                Log.e("PokemonViewModel", e.toString())
            }
        }
    }

    fun getRandomPokemon() {
        val randomIndex = (1 until 1025).random()
        viewModelScope.launch {
            val pokemon = PokemonService.retrofit.fetchPokemonById(randomIndex)
            _quizState.update { it.copy(currentPokemon = pokemon) }
        }
    }

    fun updateUserInput(input: String) {
        _quizState.update { it.copy(pokemonName = input) }
    }

    fun validateAnswer() {
        val currentState = _quizState.value
        val isCorrect = currentState.currentPokemon?.name?.fr?.lowercase()?.removeAccents() == currentState.pokemonName.lowercase().removeAccents()

        _quizState.update {
            it.copy(
                correctAnswers = if (isCorrect) it.correctAnswers + 1 else it.correctAnswers,
                totalQuestions = it.totalQuestions + 1,
                lastResult = isCorrect,
                pokemonName = ""
            )
        }

        if (_quizState.value.totalQuestions >= 3) {
            endQuiz()
        } else {
            getRandomPokemon()
        }
    }

    private fun endQuiz() {
        val currentState = _quizState.value
        val elapsedTime = System.currentTimeMillis() - (currentState.startTime ?: System.currentTimeMillis())

        _quizState.update {
            it.copy(
                quizCompleted = true,
                elapsedTime = elapsedTime / 1000
            )
        }
    }

    fun startQuiz() {
        _quizState.update { it.copy(startTime = System.currentTimeMillis()) }
    }
}

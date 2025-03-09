package com.example.model

data class QuizState(
    val currentPokemon: Pokemon? = null,
    val pokemonName: String = "",
    val correctAnswers: Int = 0,
    val totalQuestions: Int = 0,
    val quizCompleted: Boolean = false,
    val startTime: Long? = null,
    val elapsedTime: Long = 0,
    val lastResult: Boolean? = null
)

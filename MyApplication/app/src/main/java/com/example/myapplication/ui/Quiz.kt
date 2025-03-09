package com.example.myapplication.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.viewModel.PokemonViewModel
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import com.example.model.QuizState
import com.example.myapplication.ui.composable.PokemonDisplay
import com.example.myapplication.ui.composable.TopBar

@Composable
fun Quiz(
    navController: NavController,
    viewModel: PokemonViewModel = viewModel()
) {
    val quizState by viewModel.quizState.collectAsState()
    LaunchedEffect(quizState.startTime, quizState.quizCompleted) {
        if (quizState.startTime == null) {
            viewModel.startQuiz()
        }
        if (quizState.quizCompleted) {
            navController.navigate("result/${quizState.correctAnswers}/${quizState.totalQuestions}/${quizState.elapsedTime}")
        }
    }

    Scaffold(
        topBar = { TopBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            when {
                quizState.currentPokemon == null -> LoadingScreen()
                else -> QuizContent(
                    quizState = quizState,
                    onValidate = viewModel::validateAnswer,
                    onPokemonNameChange = viewModel::updateUserInput
                )
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Chargement...")
    }
}

@Composable
private fun QuizContent(
    quizState: QuizState,
    onValidate: () -> Unit,
    onPokemonNameChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        quizState.currentPokemon?.let {
            PokemonDisplay(it, true)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ValidationForm(
            pokemonName = quizState.pokemonName,
            onPokemonNameChange = onPokemonNameChange,
            onValidate = onValidate
        )
        ValidationMessage(quizState.lastResult)
        Score(
            correctAnswers = quizState.correctAnswers,
            totalQuestions = quizState.totalQuestions
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun ValidationForm(
    pokemonName: String,
    onPokemonNameChange: (String) -> Unit,
    onValidate: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = pokemonName,
            onValueChange = onPokemonNameChange,
            label = { Text("Entrez le nom du Pokémon") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Button(onClick = onValidate) {
            Text("Valider")
        }
    }
}

@Composable
private fun ValidationMessage(result: Boolean?) {
    result?.let {
        val (message, color) = if (it) "Bravo !" to Color.Green else "Essaie encore !" to Color.Red
        Text(text = message, color = color)
    }
}

@Composable
private fun Score(correctAnswers: Int, totalQuestions: Int) {
    Text("Score : $correctAnswers / $totalQuestions")
}

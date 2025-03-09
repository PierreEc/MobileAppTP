package com.example.myapplication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.myapplication.navigation.Navigation

@Composable
fun Result(navController: NavController, correctAnswers: Int, totalQuestions: Int, timeInSeconds: Long) {
    val userName = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Quiz terminé !", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Votre score : $correctAnswers / $totalQuestions", fontSize = 24.sp)
        Text("Temps : ${timeInSeconds} secondes", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = userName.value,
            onValueChange = { userName.value = it },
            label = { Text("Entrez votre nom") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate(Navigation.Home.route) {
                popUpTo(Navigation.Quiz.route) { inclusive = true }
            }
        }) {
            Text("Valider et Retourner à l'accueil")
        }
    }
}

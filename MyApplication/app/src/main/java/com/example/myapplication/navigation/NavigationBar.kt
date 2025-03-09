package com.example.myapplication.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.Home
import com.example.myapplication.ui.PokemonList
import com.example.myapplication.ui.Quiz
import com.example.myapplication.ui.Ranking
import com.example.myapplication.ui.Result

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Navigation.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Navigation.Home.route) { Home(navController) }
            composable(Navigation.Infos.route) { PokemonList() }
            composable(Navigation.Ranking.route) { Ranking() }
            composable(Navigation.Quiz.route) { Quiz(navController) }
            composable("result/{correctAnswers}/{totalQuestions}/{timeInSeconds}") { backStackEntry ->
                val correctAnswers = backStackEntry.arguments?.getString("correctAnswers")?.toInt() ?: 0
                val totalQuestions = backStackEntry.arguments?.getString("totalQuestions")?.toInt() ?: 0
                val timeInSeconds = backStackEntry.arguments?.getString("timeInSeconds")?.toLong() ?: 0
                Result(navController, correctAnswers, totalQuestions, timeInSeconds)
            }
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    val screens = listOf(Navigation.Home, Navigation.Infos, Navigation.Ranking)
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
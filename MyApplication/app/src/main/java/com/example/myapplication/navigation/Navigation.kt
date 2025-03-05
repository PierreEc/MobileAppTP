package com.example.myapplication.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Navigation(val route: String, val title: String, val icon: ImageVector) {
    object Home: Navigation("home", "Accueil", Icons.Filled.PlayArrow)
    object Infos: Navigation("infos", "Infos", Icons.Filled.Info)
    object Ranking: Navigation("ranking", "Classement", Icons.Filled.Edit)
    object Quiz: Navigation("quiz", "Quiz", Icons.Filled.PlayArrow)
}
package com.example.myapplication.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.viewModel.PokemonViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Quiz(navController: NavController) {
    val viewModel: PokemonViewModel = viewModel()
    val pokemoncol by viewModel.pokemon.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Small Top App Bar")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding), // Correction ici
            contentAlignment = Alignment.Center
        ) {
            if (pokemoncol.isEmpty()) {
                Text("Chargement...")

            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(pokemoncol) { pokemon ->
                        Card(modifier = Modifier.fillMaxSize()) {
                            Text(pokemon.name.fr, fontSize = 24.sp)
                            AsyncImage(
                                model = pokemon.sprites.regular,
                                contentDescription = pokemon.name.fr,
                                modifier = Modifier.size(128.dp),
                                alignment = Alignment.Center,
                                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
                            )
                        }

                    }
                }
            }

        }
    }

}
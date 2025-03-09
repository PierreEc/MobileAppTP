package com.example.myapplication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.composable.PokemonDisplay
import com.example.myapplication.viewModel.PokemonViewModel

@Composable
fun PokemonList() {
    val viewModel: PokemonViewModel = viewModel()
    val collection by viewModel.pokemons.collectAsState()
    if (collection.isEmpty()) {
        Text("Chargement...")
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            items(collection) { item ->
                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    PokemonDisplay(item)
                }
            }
        }
    }
}
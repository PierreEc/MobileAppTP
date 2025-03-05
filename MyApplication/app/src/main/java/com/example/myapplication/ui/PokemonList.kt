package com.example.myapplication.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.myapplication.viewModel.PokemonViewModel


@Composable
fun PokemonList() {
    val viewModel: PokemonViewModel = viewModel()
    val collection by viewModel.pokemon.collectAsState()
    if(collection.isEmpty()){
        Text("Chargement...")

    }else{

        LazyColumn (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            items(collection) { item ->
                Card(modifier = Modifier.fillMaxSize()) {
                    Text(item.name.fr, fontSize = 24.sp)
                    AsyncImage(
                        model = item.sprites.regular,
                        contentDescription = item.name.fr,
                        modifier = Modifier.size(128.dp),
                        alignment = Alignment.Center
                    )
                }
            }
        }
    }
}

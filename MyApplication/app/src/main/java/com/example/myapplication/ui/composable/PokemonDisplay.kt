package com.example.myapplication.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.Pokemon

@Composable
fun PokemonDisplay(pokemon: Pokemon, isGray: Boolean = false) {
    val colorFilter = if (isGray) {
        ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
    } else {
        null
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = pokemon.name.fr,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        AsyncImage(
            model = pokemon.sprites.regular,
            contentDescription = pokemon.name.fr,
            modifier = Modifier
                .size(128.dp)
                .align(Alignment.CenterHorizontally),
            colorFilter = colorFilter
        )
    }
}

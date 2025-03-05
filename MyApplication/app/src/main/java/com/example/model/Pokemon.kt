package com.example.model
data class Pokemon(
    val name: Name,
    val category: String,
    val sprites: Sprite,

)
data class Name(
    val fr : String
)
data class Sprite(
    val regular: String
)
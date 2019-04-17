package com.itomkinas.botgame.gameengine.models

data class OnTheMove(
    val id: String,
    val from: Base,
    val to: Base,
    val count: Int,
    val totalDuration: Int,
    val currentPosition: Int,
    val coordinates: Coordinates,
    val owner: String
)
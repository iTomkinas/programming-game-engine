package com.itomkinas.botgame.gameengine.models

data class Base(
    val id: String,
    val coordinates: Coordinates,
    val productionRate: Int,
    val owner: String?,
    val occupancyCount: Int
)


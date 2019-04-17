package com.itomkinas.botgame.gameengine.models

import kotlin.math.pow

data class Coordinates(
    val x: Float,
    val y: Float
)

fun Coordinates.distanceTo(coordinates: Coordinates): Int {
    val d = (coordinates.x - this.x).pow(2) + (coordinates.y - this.y).pow(2)
    return (Math.sqrt(d.toDouble()) / 10).toInt()
}
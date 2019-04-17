package com.itomkinas.botgame.gameengine.models

data class Map(
    val bases: List<Base>,
    val moving: List<OnTheMove>,
    val removeWarrior: List<OnTheMove> = listOf()
)
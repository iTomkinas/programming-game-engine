package com.itomkinas.botgame.player

import com.itomkinas.botgame.gameengine.models.Map
import com.itomkinas.botgame.gameengine.models.PlayerAction

interface Player {

    fun executeActions(map: Map): List<PlayerAction>

    fun getId(): String

    fun getName(): String
}
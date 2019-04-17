package com.itomkinas.botgame.player.botimplementations

import com.itomkinas.botgame.gameengine.models.Map
import com.itomkinas.botgame.gameengine.models.PlayerAction
import com.itomkinas.botgame.player.Player
import java.util.UUID

class  DummyPlayer2 : Player {

    private val myId = UUID.randomUUID().toString()

    override fun executeActions(map: Map): List<PlayerAction> {
        return listOf()
    }

    override fun getId() = myId

    override fun getName() = "Dummy bot"
}

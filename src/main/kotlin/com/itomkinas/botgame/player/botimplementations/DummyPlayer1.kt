package com.itomkinas.botgame.player.botimplementations

import com.itomkinas.botgame.gameengine.models.Base
import com.itomkinas.botgame.gameengine.models.Map
import com.itomkinas.botgame.gameengine.models.PlayerAction
import com.itomkinas.botgame.player.Player
import java.util.UUID

class  DummyPlayer1 : Player {

    private val myId = UUID.randomUUID().toString()

    private val myBases = mutableListOf<Base>()
    private val emptyBase = mutableListOf<Base>()
    private val enemies = mutableListOf<Base>()

    private val actions = mutableListOf<PlayerAction>()


    override fun executeActions(map: Map): List<PlayerAction> {

        myBases.clear()
        emptyBase.clear()
        actions.clear()

        map.bases.forEach {
            when {
                it.owner == getId() -> myBases.add(it)
                it.owner == null -> emptyBase.add(it)
                else -> enemies.add(it)
            }
        }

        emptyBase.shuffle()
        if (emptyBase.size > 0 && myBases[0].occupancyCount > emptyBase[0].occupancyCount) {
            actions.add(
                PlayerAction.MoveWarriors(
                    from = myBases[0],
                    to = emptyBase[0],
                    count = myBases[0].occupancyCount
            ))
        }

        return actions
    }

    override fun getId() = myId

    override fun getName() = "Tom"
}

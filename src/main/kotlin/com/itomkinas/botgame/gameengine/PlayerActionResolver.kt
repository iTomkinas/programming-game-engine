package com.itomkinas.countdown.game.gameengine

import com.itomkinas.botgame.gameengine.models.Map
import com.itomkinas.botgame.gameengine.models.OnTheMove
import com.itomkinas.botgame.player.Player
import com.itomkinas.botgame.gameengine.models.PlayerAction
import com.itomkinas.botgame.gameengine.models.distanceTo
import java.util.UUID

class PlayerActionResolver {

    fun resolve(
        map: Map,
        actions: List<PlayerAction>,
        player: Player
    ): Map {
        var newMap = map
        actions.forEach { action -> newMap = resolveAction(newMap, action, player) }
        return newMap
    }

    private fun resolveAction(
        map: Map,
        action: PlayerAction,
        player: Player
    ): Map {
        return when (action) {
            is PlayerAction.IncreaseProductionRate -> map
            is PlayerAction.MoveWarriors -> moveWarriors(map, action, player)
            is PlayerAction.SendBomb -> map
        }
    }

    private fun moveWarriors(
        map: Map,
        playerAction: PlayerAction.MoveWarriors,
        player: Player
    ): Map {

        val baseFrom = map.bases.find { it.id == playerAction.from.id }

        if (baseFrom?.occupancyCount ?: 0 >= playerAction.count) {

            val bases = map.bases.map {
                if (it.id == baseFrom?.id) {
                    it.copy(occupancyCount = it.occupancyCount - playerAction.count)
                } else {
                    it
                }
            }

            val list = map.moving.toMutableList()
            list.add(
                OnTheMove(
                    id = UUID.randomUUID().toString(),
                    from = playerAction.from,
                    to = playerAction.to,
                    count = playerAction.count,
                    totalDuration = playerAction.from.coordinates.distanceTo(playerAction.to.coordinates),
                    currentPosition = 0,
                    coordinates = playerAction.from.coordinates,
                    owner = player.getId()
                )
            )

            return map.copy(moving = list, bases = bases)
        } else {
            return map
        }
    }
}
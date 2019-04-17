package com.itomkinas.countdown.game.gameengine

import com.itomkinas.botgame.gameengine.models.Coordinates
import com.itomkinas.botgame.gameengine.models.Map
import com.itomkinas.botgame.gameengine.models.OnTheMove

class GameUnitsTurnResolver {

    fun resolveTurn(map: Map): Map {

        val completedMap = map.moving.filter { it.totalDuration < it.currentPosition }
        val willMove = map.moving.filter { it.totalDuration >= it.currentPosition }
        val removeWarriors = mutableListOf<OnTheMove>()

        val bases = map.bases.map { base ->
            completedMap.forEach { completed ->
                if (base.id == completed.to.id) {
                    if (base.occupancyCount < completed.count) {
                        println("attacked and won")
                        removeWarriors.add(completed)
                        return@map base.copy(occupancyCount = completed.count - base.occupancyCount,
                                owner = completed.owner)
                    } else {
                        println("did not won")
                        return@map base.copy(occupancyCount = base.occupancyCount - completed.count,
                                owner = completed.owner)
                    }
                }
            }

            base
        }


        val moving = willMove.map {
            it.copy(
                    currentPosition = it.currentPosition + 1,
                    coordinates = calculateCoordinates(it)
            )
        }

        return map.copy(moving = moving,
                bases = bases,
                removeWarrior = removeWarriors)
    }

    private fun calculateCoordinates(onTheMove: OnTheMove): Coordinates {
        val from = onTheMove.from.coordinates
        val to = onTheMove.to.coordinates

        val xDif = Math.abs(from.x - to.x) / onTheMove.totalDuration * onTheMove.currentPosition
        val yDif = Math.abs(from.y - to.y) / onTheMove.totalDuration * onTheMove.currentPosition

        val newX = if (from.x < to.x) {
            from.x + xDif
        } else {
            from.x - xDif
        }

        val newY = if (from.y < to.y) {
            from.y + yDif
        } else {
            from.y - yDif
        }

        if (onTheMove.totalDuration <= onTheMove.currentPosition) {
            return to
        }

        return Coordinates(newX, newY)
    }
}
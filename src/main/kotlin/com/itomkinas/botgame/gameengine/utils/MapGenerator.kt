package com.itomkinas.botgame.gameengine.utils

import com.itomkinas.botgame.gameengine.models.Base
import com.itomkinas.botgame.gameengine.models.Coordinates
import com.itomkinas.botgame.gameengine.models.Map
import com.itomkinas.botgame.player.Player
import com.itomkinas.botgame.gameengine.models.distanceTo
import java.util.*

class MapGenerator {

    fun generate(
        baseCount: Int,
        players: List<Player>
    ): Map {

        val bases = mutableListOf<Base>()

        players.forEach { player ->
            val playerBase = Base(
                id = UUID.randomUUID().toString(),
                coordinates = getRandomCoordinates(bases, true),
                owner = player.getId(),
                productionRate = 1,
                occupancyCount = 5
            )

            bases.add(playerBase)
        }

        for (i in 1..baseCount) {
            val emptyBase = Base(
                id = UUID.randomUUID().toString(),
                coordinates = getRandomCoordinates(bases, false),
                owner = null,
                productionRate = 1,
                occupancyCount = (Math.random() * 5).toInt()
            )

            bases.add(emptyBase)
        }

        return Map(bases = bases, moving = listOf())

    }

    private fun getRandomCoordinates(bases: List<Base>, isPlayer: Boolean): Coordinates {
        var count = 0
        var minDistance = if (isPlayer) 100 else 5
        while (true) {
            count += 1
            if (count / 500 > 1) {
                count = 0
                minDistance -= 1
            }

            val mapSize = 300

            val coordinates = Coordinates(
                Math.random().toFloat() * mapSize,
                Math.random().toFloat() * mapSize
            )
            val found = bases.filter { it.coordinates.distanceTo(coordinates) < 4 }.isNotEmpty()
            val isByEdge = (coordinates.x < 20 && coordinates.x > (mapSize - 20)) &&
                    (coordinates.y < 20 && coordinates.y > (mapSize - 20))
            if (!found && !isByEdge) return coordinates
        }
    }
}
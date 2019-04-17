package com.itomkinas.countdown.game.gameengine

import com.itomkinas.botgame.gameengine.models.Base
import com.itomkinas.botgame.gameengine.models.Map

class GameProductionResolver {

    fun resolve(map: Map): Map {
        val bases = mutableListOf<Base>()

        map.bases.forEach { base ->
            val isPlayer =  !base.owner.isNullOrEmpty()
            val increase =  if (isPlayer) base.productionRate else 0
            bases.add(base.copy(occupancyCount = base.occupancyCount + increase))
        }

        return map.copy(bases = bases)
    }
}
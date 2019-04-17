package com.itomkinas.botgame

import com.google.gson.Gson
import com.itomkinas.botgame.gameengine.models.Map
import com.itomkinas.countdown.game.gameengine.GameProductionResolver
import com.itomkinas.countdown.game.gameengine.PlayerActionResolver
import com.itomkinas.countdown.game.gameengine.GameUnitsTurnResolver
import com.itomkinas.botgame.player.botimplementations.DummyPlayer1
import com.itomkinas.botgame.player.Player
import com.itomkinas.botgame.player.botimplementations.DummyPlayer2
import com.itomkinas.botgame.gameengine.utils.MapGenerator
import java.io.FileWriter
import java.io.IOException

class Game(
    private val players: List<Player>,
    private val playerActionResolver: PlayerActionResolver,
    private val gameUnitsTurnResolver: GameUnitsTurnResolver,
    private val productionResolver: GameProductionResolver
) {

    private val gameHistory = mutableListOf<Map>()

    fun start() {
        var map = MapGenerator().generate(6, players)

        gameHistory.add(map)

        for (i in 1..numbersOfTurnsPerGame) {
            players.forEach { player ->
                val actions = player.executeActions(map)
                map = playerActionResolver.resolve(map, actions, player)
            }

            map = gameUnitsTurnResolver.resolveTurn(map)
            map = productionResolver.resolve(map)
            map = gameUnitsTurnResolver.resolveTurn(map)
            gameHistory.add(map)
        }

        println("game history")
        println(gameHistory)

        map.bases.forEach {
            println("count: ${it.occupancyCount}")
        }

        saveGameHistory(gameHistory)
    }

    companion object {
        private const val numbersOfTurnsPerGame = 500
    }
}

private fun saveGameHistory(history: List<Map>) {
    try {
        FileWriter("game.json").use { file ->
            val json = Gson().toJson(history)
            file.write(json)
            file.flush()
            println("creating file")
        }
    } catch (e: IOException) {
        print("exception")
        e.printStackTrace()
    }
}

fun main() {
    val game = Game(
        listOf(
            DummyPlayer1(),
            DummyPlayer2(),
            DummyPlayer2(),
            DummyPlayer1()
        ),
        PlayerActionResolver(),
        GameUnitsTurnResolver(),
        GameProductionResolver()
    )

    game.start()
}
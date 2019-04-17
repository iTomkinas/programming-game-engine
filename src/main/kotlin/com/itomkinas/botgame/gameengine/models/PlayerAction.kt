package com.itomkinas.botgame.gameengine.models

sealed class PlayerAction {

    data class IncreaseProductionRate(
            val from: Base
    ): PlayerAction()

    data class MoveWarriors(
        val from: Base,
        val to: Base,
        val count: Int
    ): PlayerAction()

    data class SendBomb(
        val from: Base,
        val to: Base
    ): PlayerAction()
}
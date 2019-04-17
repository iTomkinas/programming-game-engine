package com.itomkinas.botgame.gameengine.models

sealed class TurnResult {

    object Continue

    data class Winner(val id: String)
}
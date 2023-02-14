package de.mocoware.viewmodel

import androidx.lifecycle.ViewModel
import de.mocoware.model.Game
import de.mocoware.model.GameConnection
import kotlin.math.round

class CreateGameViewModel() : ViewModel() {

    fun createGameAsHost(name: String, rounds: Int, online: Boolean){
        GameConnection.setCurrentGame(Game(name, rounds))
        GameConnection.setOnlineMode(online)
        GameConnection.setHost(true)
    }

}
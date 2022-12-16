package de.mocoware.viewmodel

import androidx.lifecycle.ViewModel
import de.mocoware.model.GameConnection
import kotlin.math.round

class CreateGameViewModel() : ViewModel() {

    fun createGameAsHost(name: String, rounds: Int, online: Boolean){
        GameConnection.addNewGame(name, rounds)
        GameConnection.setOnlineMode(online)
    }


}
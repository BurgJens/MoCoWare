package com.example.testalex.viewmodel

import androidx.lifecycle.ViewModel
import com.example.testalex.model.Game
import com.example.testalex.model.GameConnection
import kotlin.math.round

class CreateGameViewModel() : ViewModel() {

    fun createGameAsHost(name: String, rounds: Int, online: Boolean){
        GameConnection.setCurrentGame(Game(name, rounds))
        GameConnection.setOnlineMode(online)
    }


}
package com.example.testalex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testalex.model.AvailableGame
import com.example.testalex.model.Game
import com.example.testalex.model.GameConnection


class JoinGameViewModel : ViewModel() {

    private var gameID = ""

    private val _availableGames : MutableLiveData<List<AvailableGame>> = MutableLiveData<List<AvailableGame>>()
    val availableGames : LiveData<List<AvailableGame>> = _availableGames

    fun getAvailableGames(){
        _availableGames.postValue(GameConnection.getAvailableGames())
    }

    fun setGameID(newID : String){
        gameID = newID
    }

    fun getGameID() = gameID

    fun getGameByID(id : String):Game{
        return GameConnection.getGameByID(id)
    }
}
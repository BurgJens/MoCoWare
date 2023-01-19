package de.mocoware.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.mocoware.model.AvailableGame
import de.mocoware.model.Game
import de.mocoware.model.GameConnection
import de.mocoware.model.minigames.PushButtonsAway

class TestViewModel : ViewModel() {

    var gameData = PushButtonsAway()

    private val _gameDataLive : MutableLiveData<PushButtonsAway> = MutableLiveData<PushButtonsAway>()
    val gameDataLive : LiveData<PushButtonsAway> = _gameDataLive


    init {
        _gameDataLive.postValue(gameData)
    }

    fun updateGamedata(){
        println("update")
        _gameDataLive.postValue(gameData.getNew())
    }

}
package de.mocoware.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.mocoware.model.minigames.DataMGannoyingButtons
import de.mocoware.model.minigames.GameData
import de.mocoware.model.minigames.MGannoyingButtons
import de.mocoware.model.minigames.MiniGame

class TestViewModel : ViewModel() {

    var i = 0

    var game = MGannoyingButtons()

    val gameDataLive = MutableLiveData<MiniGame>()

    init {
        gameDataLive.postValue(game)
    }

    fun updateGamedata(){
        gameDataLive.postValue(game.getNew())
    }

}
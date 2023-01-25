package de.mocoware.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.mocoware.model.minigames.GameData
import de.mocoware.model.minigames.MGannoyingButtons
import de.mocoware.view.navigation.NavMG

class TestViewModel : ViewModel() {

    var game = mutableListOf(
        MGannoyingButtons(),
        MGannoyingButtons(),
        MGannoyingButtons(),
        MGannoyingButtons(),
        MGannoyingButtons(),
        MGannoyingButtons(),
        MGannoyingButtons()
    )

    var currentMG = game[0]

    var currentGameData : GameData = currentMG.gameData

    val gameDataLive = MutableLiveData<GameData>()

    var navigateNext = NavMG.MGannoyingButton.route

    init {
        gameDataLive.postValue(currentGameData)
    }

    fun updateGamedata(){
        gameDataLive.postValue(currentGameData)
    }

    fun finishGame(){
        currentMG = game[game.indexOf(currentMG)+1]
        currentGameData = currentMG.gameData
        updateGamedata()
    }

}
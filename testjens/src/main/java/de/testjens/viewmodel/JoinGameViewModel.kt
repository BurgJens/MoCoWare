package de.testjens.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.testjens.model.AvailableGame
import de.testjens.model.Game
import de.testjens.model.GameList

open class JoinGameViewModel : ViewModel() {

    val gameList = GameList()

    private val _availableGames : MutableLiveData<List<AvailableGame>> = MutableLiveData<List<AvailableGame>>()
    val availableGames : LiveData<List<AvailableGame>> = _availableGames

    fun addGame(newGame : Game){
        gameList.addGame(newGame)
        getAvailableGames()
    }

    fun getAvailableGames(){
        _availableGames.postValue(gameList.getAvailableGames())
    }

//    fun setNextScreen(path: String){
//        this.nextScreen = path
//    }

}
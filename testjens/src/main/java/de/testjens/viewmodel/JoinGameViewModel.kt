package de.testjens.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.testjens.model.AvailableGame
import de.testjens.model.Game
import de.testjens.model.GameList
import de.testjens.view.NavScreen

class JoinGameViewModel : ViewModel() {

    private var gameID = ""

    val gameList = GameList()

    private val _availableGames : MutableLiveData<List<AvailableGame>> = MutableLiveData<List<AvailableGame>>()
    val availableGames : LiveData<List<AvailableGame>> = _availableGames

    fun addGame(gameName : String){
        gameList.addGame(Game(gameName))
        getAvailableGames()
    }

    fun getAvailableGames(){
        _availableGames.postValue(gameList.getAvailableGames())
    }

    fun setGameID(newID : String){
        gameID = newID
    }

    fun getGameID() = gameID

    fun getGameByID(id : String):Game{
        return gameList.getGameByID(id)
    }
}
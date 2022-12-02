package de.testjens.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.testjens.model.Game

class GameViewModel() : ViewModel(){

    private var game : Game = Game("anusBenus")

    private val _game : MutableLiveData<Game> = MutableLiveData<Game>(game)
    val liveGame : LiveData<Game> = _game

    fun withGame(newGame: Game): GameViewModel{
        setGame(newGame)
        return this
    }

    fun setGame(newGame: Game){
        game = newGame
        _game.postValue(game)
    }
}
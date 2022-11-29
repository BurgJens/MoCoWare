package de.testjens.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GameList () {

//    private val gameList = mutableListOf<Game>()
//    private val games = MutableLiveData<List<Game>>()
//
//    init {
//        addGame(Game("Test 1"))
//        addGame(Game("Test 2"))
//        addGame(Game("Test 3"))
//    }
//
//    fun addGame(game : Game){
//        gameList.add(game)
//        games.postValue(gameList)
//    }
//
//    fun getGames() : LiveData<List<Game>>{
//        return games
//    }

    val games = mutableListOf<Game>()

        init {
            games.add(Game("Test 1"))
            games.add(Game("Test 2"))
            games.add(Game("Test 3"))
    }

    fun getAvailableGames() : MutableList<Game>{
        return games
    }
}
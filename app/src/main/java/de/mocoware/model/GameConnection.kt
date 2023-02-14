package de.mocoware.model

import android.util.Log
import com.google.android.gms.nearby.*
import java.util.*


data class AvailableGame(val name: String, val gameID: String)



object GameConnection{


    private var onlineMode = false

    private var host = false

    private var gameToJoin : AvailableGame? = null
    private var currentGame : Game? = null

    private val games = mutableListOf<Game>()

    fun addNewGame(name: String, rounds: Int) {
        games.add(Game(name, rounds))
    }

    fun getAvailableGames() : List<AvailableGame>{
        val list = mutableListOf<AvailableGame>()
        games.forEach{ each ->
            list.add(AvailableGame(each.getName(), each.getID()))
        }
        return list.toList()
    }

    fun getGameByID(id : String) : Game{
        val i = games.firstOrNull{ it.getID() == id.substring(1..4) }
        if (i == null){
            return Game("Wrong ID")
        }else return i
    }

    fun setOnlineMode(setMode : Boolean){
        onlineMode = setMode
    }

    fun setCurrentGame(game: Game){
        currentGame = game
    }

    fun setHost(isHost: Boolean) {
        host = isHost
    }

    fun getCurrentGame() : Game? {
        return currentGame
    }

    fun getCurrentGameName() : String?{
        return currentGame?.getName()
    }

    fun getCurrentGameID() : String?{
        return currentGame?.getID()
    }
}
package de.mocoware.model

import de.mocoware.model.minigames.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import java.util.concurrent.atomic.AtomicBoolean

enum class MiniGameEnum{
    MGannoyingButtons,
    MGlaufenWithService,
    MGshake,
    MGconfusingButtons
}

class Game (private var name: String, rounds: Int = 5){

    val initialised = AtomicBoolean(false)

    private val gameId = generateGameId()

    val miniGamesSemaphore = Semaphore(1)
    val miniGames = mutableListOf(getRandomMiniGame())

    val wonGames = mutableListOf<Boolean>()

    var currentGame = 0
    var currentRoute = miniGames[currentGame].gameRoute

    init {
        addMinigames(rounds-1)
        initialised.set(true)
    }

    private fun generateGameId() : String{
        var newID = ""
        val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        repeat(4){
            newID += charPool.random()
        }
        return newID
    }

    fun getID() : String{
        return gameId
    }

    fun getName() : String{
        return name
    }

    fun getCurrentGame() : MiniGame{
        return miniGames[currentGame]
    }

    fun nextGame(): Boolean{
        currentGame++
        if(currentGame == miniGames.size){
            currentGame = 0
            currentRoute = miniGames[currentGame].gameRoute
            return false
        }
        return true
    }

    fun routeToMG() : String{
        return miniGames[currentGame].gameRoute
    }

    fun addMinigames(amount: Int){
        CoroutineScope(Dispatchers.Main).launch{
            miniGamesSemaphore.acquire()
            miniGames.clear()
            repeat(amount){
                miniGames.add(getRandomMiniGame())
            }
            miniGamesSemaphore.release()
        }
    }

    fun getRandomMiniGame() : MiniGame{
//        val nextMinigame = MiniGameEnum.values().random()
        val nextMinigame = listOf(
            MiniGameEnum.MGannoyingButtons,
            MiniGameEnum.MGconfusingButtons
        ).random()
        when(nextMinigame){
            MiniGameEnum.MGannoyingButtons -> return (MGannoyingButtons())
            MiniGameEnum.MGlaufenWithService -> return (MGlaufenWithService())
            MiniGameEnum.MGshake ->return (MGshake())
            MiniGameEnum.MGconfusingButtons -> return (MGconfusingButtons())
        }

    }
}
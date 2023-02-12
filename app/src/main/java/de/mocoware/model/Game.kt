package de.mocoware.model

import de.mocoware.model.minigames.*

import kotlin.random.Random

enum class MiniGameEnum{
    MGannoyingButtons,
    MGlaufenWithService,
    MGshake,
    MGconfusingButtons
}

class Game (private var name: String, rounds: Int = 5){

    private var gameId = generateGameId()

    val miniGames = mutableListOf<MiniGame>()

    var currentGame = 0

    init {
        addMinigames(rounds)
    }

    private fun generateGameId() : String{
        var newID = ""
        val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        repeat(4){
            newID += charPool[Random.nextInt(charPool.size)]
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
            return false
        }
        return true
    }

    fun routeToNextMG() : String{
        return miniGames[currentGame+1].gameRoute
    }

    fun addMinigames(amount: Int){
        miniGames.clear()
        repeat(amount){
            val nextMinigame = MiniGameEnum.values().random()
//            val nextMinigame = MiniGameEnum.MGconfusingButtons
//            val nextMinigame = listOf(
//                MiniGameEnum.MGannoyingButtons,
//                MiniGameEnum.MGconfusingButtons
//            ).random()
            when(nextMinigame){
                MiniGameEnum.MGannoyingButtons -> miniGames.add(MGannoyingButtons())
                MiniGameEnum.MGlaufenWithService -> miniGames.add(MGlaufenWithService())
                MiniGameEnum.MGshake -> miniGames.add(MGshake())
                MiniGameEnum.MGconfusingButtons -> miniGames.add(MGconfusingButtons())
            }
        }
    }
}
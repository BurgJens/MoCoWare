package de.mocoware.model

import de.mocoware.model.minigames.MGannoyingButtons
import de.mocoware.model.minigames.MiniGame
import de.mocoware.model.minigames.MiniGame2
import de.mocoware.model.minigames.MiniGameListe

import kotlin.random.Random

enum class MiniGameEnum{
    MGannoyingButtons
}

class Game (private var name: String, private var rounds: Int = 5){

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

    fun nextGame(){
        currentGame++
    }

    fun addMinigames(amount: Int){
        miniGames.clear()
        val miniGameEnumValues = MiniGameEnum.values()
        val nextMinigame = MiniGameEnum.values().random()
        repeat(amount){
            when(nextMinigame){
                MiniGameEnum.MGannoyingButtons -> miniGames.add(MGannoyingButtons())
            }
        }
    }

}
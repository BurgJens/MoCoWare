package de.mocoware.model

import de.mocoware.model.minigames.*
import de.mocoware.view.navigation.NavMG

import kotlin.random.Random

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

    fun getCurrentMG() : MiniGame{
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

    fun getRouteToMG() : String{
        if (currentGame==miniGames.size-1) return NavMG.ScoreScreen.route
        return miniGames[currentGame+1].gameRoute
    }

    fun addMinigames(amount: Int){
        miniGames.clear()
        repeat(amount){
        //    val nextMinigame = MiniGameEnum.values().random()
           // val nextMinigame = MiniGameEnum.MGlaufenWithService
            val nextMinigame = listOf(
//                MiniGameEnum.MGLoRButtonMasher,
                MiniGameEnum.MGannoyingButtons,
                MiniGameEnum.MGconfusingButtons,

            //    MiniGameEnum.MGbeleuchtung,
            //   MiniGameEnum.MGshake,
//                MiniGameEnum.MGlaufenWithService
            ).random()

            when(nextMinigame){
                MiniGameEnum.MGannoyingButtons -> miniGames.add(MGannoyingButtons())
                MiniGameEnum.MGlaufenWithService -> miniGames.add(MGlaufenWithService())
                MiniGameEnum.MGshake -> miniGames.add(MGshake())
                MiniGameEnum.MGconfusingButtons -> miniGames.add(MGconfusingButtons())
                MiniGameEnum.MGbeleuchtung -> miniGames.add(MGbeleuchtung())
                MiniGameEnum.MGLoRButtonMasher -> miniGames.add(MGLoRButtonMasher())
            }
        }
    }
}
package de.testjens.model

import android.app.appsearch.StorageInfo
import de.testjens.model.minigames.MiniGame
import de.testjens.model.minigames.TestMinigame
import kotlin.random.Random

enum class MiniGameEnum{
    TestMinigame
}

class Game (val name: String){

    private var gameId = generateGameId()

    private val miniGames = mutableListOf<MiniGame>()

    init {
        addMinigames(10)
    }

    private fun generateGameId() : String{
        var newID = ""
        val charPool = listOf(('a'..'z') + ('A'..'Z') + ('0'..'9'))
        repeat(4){
            newID += charPool[Random.nextInt(charPool.size)]
        }
        return newID
    }

    fun getID() : String{
        return gameId
    }

    fun addMinigames(amount: Int){
        miniGames.clear()
        val miniGameEnumValues = MiniGameEnum.values()
        val nextMinigame = miniGameEnumValues[Random.nextInt(MiniGameEnum.values().size)]
        repeat(amount){
            when(nextMinigame){
                MiniGameEnum.TestMinigame -> miniGames.add(TestMinigame())
            }
        }
    }

}
package de.mocoware.model


import de.mocoware.model.minigames.MiniGame2
import de.mocoware.model.minigames.MiniGameListe
import kotlin.random.Random

object ActiveGame{

    var isActive = false

    private val name = ""

    private var gameId = ""

    private val miniGames = mutableListOf<MiniGame2>()

    init {
        addMinigames(10)
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

    fun addMinigames(amount: Int){
        miniGames.clear()
        val miniGameEnumValues = MiniGameEnum.values()
        val nextMinigame = miniGameEnumValues[Random.nextInt(MiniGameEnum.values().size)]
        repeat(amount){
            when(nextMinigame){
                MiniGameEnum.TestMinigame -> miniGames.add(MiniGameListe().zufallSpiel())
            }
        }
    }

}
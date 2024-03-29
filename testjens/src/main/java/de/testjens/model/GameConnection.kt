package de.testjens.model

data class AvailableGame(val name: String, val gameID: String)

object GameConnection{

    private var onlineMode = false

    private val games = mutableListOf<Game>()

    fun addGame(newGame: Game){
        games.add(newGame)
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
}
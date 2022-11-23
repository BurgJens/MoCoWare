package de.testjens.model

class Gamelist () {

    val gameList = mutableListOf<Game>()

    init {
        gameList.add(Game("1"))
        gameList.add(Game("2"))
        gameList.add(Game("3"))
    }
}
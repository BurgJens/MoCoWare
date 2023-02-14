package de.mocoware.model.minigames

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.mocoware.model.MiniGame
import de.mocoware.view.navigation.NavMG

data class DataMGLoRButtonMasher(override var data: Any) : GameData{
    var ButtonMasherList = data as MutableList<lorColorButton>
    var fakeColor = Color.Black
}


    data class lorColorButton(
        val x : Dp,
        val color : Color,
        val winButton : Boolean = false
    ){

    }


class MGLoRButtonMasher(
    override var gameData : GameData = DataMGLoRButtonMasher(mutableListOf<lorColorButton>()),
    override val gameRoute: String = NavMG.MGLoRButtonMasher.route
) : MiniGame {


    val possiblePos = mutableListOf(-150.dp, 150.dp)

    val possibleColor = mutableListOf(
        Color(0, 200, 0),
        Color(132, 132, 132),
    )

    init {
        var gameData = gameData as DataMGLoRButtonMasher
        if (gameData.ButtonMasherList.size == 0) {
            val finButtonPos = possiblePos.random()
            possiblePos.remove(finButtonPos)

            val finButtonCol = possibleColor.random()
            possibleColor.remove(finButtonCol)
            gameData.fakeColor = possibleColor.random()

            gameData.ButtonMasherList.add(
                lorColorButton(
                    finButtonPos,
                    finButtonCol,
                    winButton = true
                )
            )
            repeat(20) {
                val sumButtonPos = possiblePos.random()
                possiblePos.remove(sumButtonPos)

                val sumButtonCol = possibleColor.random()
                possibleColor.remove(sumButtonCol)

                gameData.ButtonMasherList.add(
                    lorColorButton(
                        sumButtonPos,
                        sumButtonCol
                    )
                )
            }
        }
    }
}



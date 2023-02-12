package de.mocoware.model.minigames

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.mocoware.model.MiniGame
import de.mocoware.view.navigation.NavMG


data class DataMGconfusingButtons(override var data: Any) : GameData {
    var confusingButtonList = data as MutableList<confusingColorButton>
    var confusingText = ""
    var fakeColor = Color.Black
}

data class confusingColorButton(
    val x : Dp,
    val color : Color,
    val winButton : Boolean = false
){

}

class MGconfusingButtons(
    override var gameData : GameData = DataMGconfusingButtons(mutableListOf<confusingColorButton>()),
    override val gameRoute: String = NavMG.MGconfusingButtons.route
) : MiniGame {

    val possibleText = listOf(
        "<3"
    )

    val possiblePos = mutableListOf(-150.dp,-50.dp,50.dp,150.dp)

    val possibleColor = mutableListOf(
        Color(200,0,0),
        Color(0,200,0),
        Color(0,0,200,),
        Color(200,0,200,)
    )

    init {
        var gameData = gameData as DataMGconfusingButtons
        if (gameData.confusingButtonList.size == 0) {
            val finButtonPos = possiblePos.random()
            possiblePos.remove(finButtonPos)

            val finButtonCol = possibleColor.random()
            possibleColor.remove(finButtonCol)
            gameData.fakeColor = possibleColor.random()

            if(finButtonCol == Color(200,0,0) ){
                gameData.confusingText = "Rot"
            }else if(finButtonCol == Color(0,200,0)){
                gameData.confusingText = "Grün"
            }else if(finButtonCol == Color(0,0,200,)){
                gameData.confusingText = "Blau"
            }else if(finButtonCol == Color(200,0,200,)){
                gameData.confusingText = "Lila"
            }

            gameData.confusingButtonList.add(
                confusingColorButton(
                    finButtonPos,
                    finButtonCol,
                    winButton = true
                )
            )
            repeat(3) {

                val someButtonPos = possiblePos.random()
                possiblePos.remove(someButtonPos)

                val someButtonCol = possibleColor.random()
                possibleColor.remove(someButtonCol)

                gameData.confusingButtonList.add(
                    confusingColorButton(
                        someButtonPos,
                        someButtonCol,
                    )
                )
            }
        }
    }
}
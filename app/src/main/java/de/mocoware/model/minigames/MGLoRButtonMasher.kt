package de.mocoware.model.minigames

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.mocoware.model.MiniGame
import de.mocoware.view.navigation.NavMG


data class DataMGLoRButtonMasher(override var data: Any) : GameData{
    var ButtonMasherList = data as MutableList<lorColorButton>
}


data class lorColorButton(
    val x : Dp,
    val winButton : Boolean = false,
    val color: Color,
    val looseButton : Boolean = true,
    var visible : Boolean = true,
    val update: ()->Unit
){



}


class MGLoRButtonMasher(
    override var gameData : GameData = DataMGLoRButtonMasher(mutableListOf<lorColorButton>()),
    override val gameRoute: String = NavMG.MGLoRButtonMasher.route
) : MiniGame {


    val possiblePos = mutableListOf(-150.dp, 150.dp)

    val winColor =
        Color(0, 200, 0)
    val looseColor =
        Color(132, 132, 132)

    init{
        var ButtonMasherlist = gameData as DataMGLoRButtonMasher
        if(ButtonMasherlist.ButtonMasherList.size == 0)repeat(20){
            ButtonMasherlist.ButtonMasherList.add(
                lorColorButton(
                    x = possiblePos.random(),
                    winButton = true,
                    color = winColor


                ){}
            )
                ButtonMasherlist.ButtonMasherList.add(
                    lorColorButton(
                        x = possiblePos.random(),
                        winButton = false,
                        color = looseColor
                    ){}
                )
            }


    }

}


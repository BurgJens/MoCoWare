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
    val looseButton : Boolean = true,
    var visible : Boolean = true,
    val update: ()->Unit
){



}


class MGLoRButtonMasher(
    override var gameData : GameData = DataMGLoRButtonMasher(mutableListOf<lorColorButton>()),
    override val gameRoute: String = NavMG.MGLoRButtonMasher.route
) : MiniGame {







    }




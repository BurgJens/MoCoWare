package de.mocoware.model.minigames

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.mocoware.model.MiniGame
import de.mocoware.view.navigation.NavMG


data class DataMGLoRButtonMasher(override var data: Any) : GameData{
    var buttonCardList = data as MutableList<lorColorCard>
}



data class lorColorCard(
    val finalCard : Boolean,
    var visible : Boolean = true,
    val leftcorrectButton : Boolean
){

}



class MGLoRButtonMasher(
    override var gameData : GameData = DataMGLoRButtonMasher(mutableListOf<lorColorCard>()),
    override val gameRoute: String = NavMG.MGLoRButtonMasher.route
) : MiniGame {


    val truefalselist = listOf(true, false)


    init {
        var buttonCardList = gameData as DataMGLoRButtonMasher
        if (buttonCardList.buttonCardList.size == 0) {

            buttonCardList.buttonCardList.add(
                lorColorCard(
                    finalCard = true,
                    leftcorrectButton = truefalselist.random()
                )

            )
            repeat(19) {
                buttonCardList.buttonCardList.add(
                    lorColorCard(
                        finalCard = false,
                        leftcorrectButton = truefalselist.random()
                    )

                )
            }
        }
    }
}





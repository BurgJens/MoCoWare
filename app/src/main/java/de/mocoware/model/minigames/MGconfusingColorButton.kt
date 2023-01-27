//package de.mocoware.model.minigames
//
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import de.mocoware.view.navigation.NavMG
//
//
//data class DataMGconfusingButtons(override var data: Any) : GameData {
//    var confusingButtonList = data as MutableList<confusingColorButton>
//}
//
//data class confusingColorButton(
//    val x : Dp,
//    val color : Color,
//    val finalButton : Boolean = false
//){
//
//}
//
//class MGconfusingButtons(
//    override var gameData : GameData = DataMGconfusingButtons(mutableListOf<confusingColorButton>()),
//    override val gameRoute: String = NavMG.MGannoyingButton.route
//) : MiniGame {
//
//    val possibleText = listOf(
//        "<3"
//    )
//
//    val possiblePos = mutableListOf(-75.dp,-25.dp,25.dp,75.dp)
//
//    val possibleColor = mutableListOf(
//        Color(200,0,0),
//        Color(0,200,0),
//        Color(0,0,200,),
//        Color(200,0,200,)
//    )
//
//    init {
//        var confusingColorButtonList = gameData as DataMGconfusingButtons
//        if (confusingColorButtonList.confusingButtonList.size == 0) {
//            val finButtonPos = possiblePos.random()
//            possiblePos.remove(finButtonPos)
//
//            val finButtonCol = possibleColor.random()
//            possiblePos.remove(finButtonCol)
//
//            confusingColorButtonList.confusingButtonList.add(
//                confusingColorButton(
//                    finButtonPos,
//                    finButtonCol,
//                    finalButton = true
//                )
//            )
//            repeat(3) {
//
//                val someButtonPos = possiblePos.random()
//                possiblePos.remove(someButtonPos)
//
//                val someButtonCol = possibleColor.random()
//                possiblePos.remove(someButtonCol)
//
//                confusingColorButtonList.confusingButtonList.add(
//                    confusingColorButton(
//                        someButtonPos,
//                        someButtonCol,
//                    )
//                )
//            }
//        }
//    }
//}
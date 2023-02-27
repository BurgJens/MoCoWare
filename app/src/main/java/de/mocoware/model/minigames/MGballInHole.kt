package de.mocoware.model.minigames

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.mocoware.model.MiniGame
import de.mocoware.view.navigation.NavMG
import kotlin.random.Random

data class DataMGballInHole(override var data: Any) : GameData {
    var holePos = data as Pair<Dp,Dp>
}

//data class AnnoyingButton(
//    val finalButton : Boolean,
//    val buttonText : String = "Here!",
//    val offsetX : Int = Random.nextInt(-130, 130),
//    val offsetY: Int = Random.nextInt(-130, 130),
//    val rotation : Float = Random.nextInt(0, 360).toFloat(),
//    val color : Color = Color(0,250,0),
//    var visible : Boolean = true,
//    val update: () -> Unit){
//
//    fun click(){
//        update()
//        visible = false
//    }
//}

class MGballInHole(
    override var gameData : GameData = DataMGballInHole(Pair(0.dp,0.dp)),
    override val gameRoute: String = NavMG.MGballInHole.route
) : MiniGame {


    init {
        if (gameData.data == Pair(0.dp,0.dp)) {
            val degree = (1..360).toList()
            val radius = 20
            val randomAngle = Math.toRadians(degree.random().toDouble())
            val x = Math.cos(randomAngle)*radius
            val y = Math.sin(randomAngle)*radius
            gameData.data = Pair(x.dp, y.dp)
        }

    }
}
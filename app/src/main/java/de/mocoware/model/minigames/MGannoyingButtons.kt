package de.mocoware.model.minigames

import androidx.compose.ui.graphics.Color
import de.mocoware.model.MiniGame
import de.mocoware.view.navigation.NavMG
import kotlin.random.Random

data class DataMGannoyingButtons(override var data: Any) : GameData {
    var annoyingButtonList = data as MutableList<AnnoyingButton>
}

data class AnnoyingButton(
    val finalButton : Boolean,
    val buttonText : String = "Here!",
    val offsetX : Int = Random.nextInt(-130, 130),
    val offsetY: Int = Random.nextInt(-130, 130),
    val rotation : Float = Random.nextInt(0, 360).toFloat(),
    val color : Color = Color(0,250,0),
    var visible : Boolean = true,
    val update: () -> Unit){

    fun click(){
        update()
        visible = false
    }
}

class MGannoyingButtons(
    override var gameData : GameData = DataMGannoyingButtons(mutableListOf<AnnoyingButton>()),
    override val gameRoute: String = NavMG.MGannoyingButton.route
) : MiniGame {

    val possibleText = listOf(
        "Yolo!",":3",":O",":D","D:","( ͡❛ ͜ʖ ͡❛)","(ㆆ_ㆆ)","3===>","(っ＾▿＾)っ","( ˘︹˘ )",
        "( ͡ಠ ͜ʖ ͡ಠ)",";_;"
    )

    val possibleColor = listOf(
        Color(200,0,0),
        Color(250,0,0),
        Color(0,150,0),
        Color(0,200,0),
        Color(0,0,200,),
        Color(0,0,250,),
        Color(200,0,200),
        Color(250,0,250),
        Color(200,200,0),
        Color(250,250,0),
        Color(0,150,150),
        Color(0,200,200,),
        Color(0,250,250,),
    )

    init {
        var annoyingButtonList = gameData as DataMGannoyingButtons
        if (annoyingButtonList.annoyingButtonList.size == 0) {
            annoyingButtonList.annoyingButtonList.add(
                AnnoyingButton(
                    finalButton = true
                ){}
            )
            repeat(100) {
                annoyingButtonList.annoyingButtonList.add(
                    AnnoyingButton(
                        finalButton = false,
                        buttonText = possibleText.random(),
                        color = possibleColor.random()
                    ){}
                )
            }
        }
    }
}
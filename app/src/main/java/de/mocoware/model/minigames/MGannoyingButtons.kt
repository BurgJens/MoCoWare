package de.mocoware.model.minigames

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

data class DataMGannoyingButtons(var data: MutableList<AnnoyingButton>) : GameData()

data class AnnoyingButton(
    val finalButton : Boolean,
    val buttonText : String = "Here!",
    val offsetX : Int = Random.nextInt(-130, 130),
    val offsetY: Int = Random.nextInt(-130, 130),
    val rotation : Float = Random.nextInt(0, 360).toFloat(),
    val color : Color = Color(0,250,0),
    var clicked : Boolean = false,
    val update: () -> Unit){


    fun click(){
        update()
        clicked = true
    }
}

class MGannoyingButtons(
    val gameData : DataMGannoyingButtons = DataMGannoyingButtons(mutableListOf()))
    : MiniGame(){

    val possibleText = listOf(
        "Yolo!",":3",":O",":D","D:","( ͡❛ ͜ʖ ͡❛)","(ㆆ_ㆆ)","3===>","(っ＾▿＾)っ","( ˘︹˘ )",
        "( ͡ಠ ͜ʖ ͡ಠ)", ";_;"
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
        if (gameData.data.size == 0) {
            gameData.data.add(
                AnnoyingButton(
                    finalButton = true
                ){}
            )
            repeat(100) {
                gameData.data.add(
                    AnnoyingButton(
                        finalButton = false,
                        buttonText = possibleText[Random.nextInt(0, possibleText.size)],
                        color = possibleColor[Random.nextInt(0, possibleColor.size)]
                    ){}
                )
            }
        }
    }
}
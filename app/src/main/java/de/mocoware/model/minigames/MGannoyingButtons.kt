package de.mocoware.model.minigames

import kotlin.random.Random

data class DataMGannoyingButtons(var data: MutableList<AnnoyingButton>) : GameData()

class AnnoyingButton(val finalButton : Boolean, val offsetX : Int, val offsetY: Int, val update: () -> Unit){

    private var clicked = false
    var text : String
    val rotation = Random.nextInt(-360,360).toFloat()
    var possibleText = listOf("yolo",":3",":O",":O","( ͡❛ ͜ʖ ͡❛)","(ㆆ_ㆆ)","3===>","(っ＾▿＾)っ","( ˘︹˘ )","( ͡ಠ ͜ʖ ͡ಠ)")


    init {
        text = possibleText[Random.nextInt(0, possibleText.size)]
    }

    fun click(){
        update()
        clicked = true
    }

    fun getClicked() = clicked
}

class MGannoyingButtons(var buttonList: MutableList<AnnoyingButton> = mutableListOf(), var update: Boolean = true) : MiniGame() {
    init {
        if (buttonList.size == 0) {
            repeat(100) {
                buttonList.add(
                    AnnoyingButton(
                        false,
                        Random.nextInt(-150, 150),
                        Random.nextInt(-150, 150)
                    ) { update() }
                )
            }
            buttonList.add(
                AnnoyingButton(
                    true,
                    Random.nextInt(-130, 130),
                    Random.nextInt(-130, 130)
                ) { update() }
            )
        }
    }

    fun update(){
        println("GUONSUODGNUOSDNGUODNUGUOGNUOND")
        update = !update
    }

    fun getNew(): MGannoyingButtons = MGannoyingButtons(buttonList)

    fun getGameData(): DataMGannoyingButtons = DataMGannoyingButtons(buttonList)

}
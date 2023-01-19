package de.mocoware.model.minigames

import kotlin.random.Random


class AnnoyingButton(val finalButton : Boolean, val offsetX : Int, val offsetY: Int){
    private var clicked = false

    fun click(){
        clicked = true
    }

    fun getClicked() = clicked
}

class PushButtonsAway(var buttonList: MutableList<AnnoyingButton> = mutableListOf()) : MiniGame {
    init {
        if (buttonList.size == 0) {
            repeat(50) {
                buttonList.add(
                    AnnoyingButton(
                        false,
                        Random.nextInt(-100, 100),
                        Random.nextInt(-100, 100)
                    )
                )
            }
            buttonList.add(
                AnnoyingButton(
                    true,
                    Random.nextInt(-100, 100),
                    Random.nextInt(-100, 100)
                )
            )
        }
    }

    fun getNew(): PushButtonsAway = PushButtonsAway(buttonList)
}
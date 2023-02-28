package de.mocoware.model

import de.mocoware.view.navigation.NavMG

enum class MiniGameEnum{
    MGannoyingButtons,
    MGlaufenWithService,
    MGshake,
    MGconfusingButtons,
    MGbeleuchtung,
    MGLoRButtonMasher,
    MGballInHole
}

fun navMGrouteToMinigameEnum(route : String) : MiniGameEnum{
    when (route){
        NavMG.MGannoyingButton.route -> return MiniGameEnum.MGannoyingButtons
        NavMG.MGlaufenWithSerivce.route -> return MiniGameEnum.MGlaufenWithService
        NavMG.MGshake.route -> return MiniGameEnum.MGshake
        NavMG.MGconfusingButtons.route -> return MiniGameEnum.MGconfusingButtons
        NavMG.MGbeleuchtung.route -> return MiniGameEnum.MGbeleuchtung
        NavMG.MGLoRButtonMasher.route -> return MiniGameEnum.MGLoRButtonMasher
        NavMG.MGballInHole.route -> return MiniGameEnum.MGballInHole
        else -> return MiniGameEnum.MGannoyingButtons
    }
}
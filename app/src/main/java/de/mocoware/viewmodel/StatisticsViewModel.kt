package de.mocoware.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.mocoware.model.MiniGameEnum
import de.mocoware.model.PLAYERNAME_DEFAULT
import de.mocoware.model.PlayedGamesDataStore
import de.mocoware.model.getGlobalPlayerName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore

class StatisticsViewModel : ViewModel() {

    val gameStatsSema = Semaphore(1)
    var gameStats = mutableListOf<Triple<String,Int,Int>>()
    val gameStatsLive = MutableLiveData(gameStats)

    val playerNameSema = Semaphore(1)
    var playerName = PLAYERNAME_DEFAULT
    val playerNameLive = MutableLiveData(playerName)

    fun updateGameStats(context : Context){
        CoroutineScope(Dispatchers.Default).launch {
            var newGameStats = mutableListOf<Triple<String,Int,Int>>()
            for (each in MiniGameEnum.values()){
                newGameStats.add(Triple(
                    each.toString(),
                    PlayedGamesDataStore.getMGplayedCount(context, each),
                    PlayedGamesDataStore.getMGwonCount(context, each)
                ))
            }
            gameStatsSema.acquire()
            gameStats = newGameStats
            gameStatsLive.postValue(gameStats)
            gameStatsSema.release()
        }
    }

    fun updatePlayerName(context : Context){
        CoroutineScope(Dispatchers.Default).launch {
            playerNameSema.acquire()
            playerName = getGlobalPlayerName(context)
            playerNameLive.postValue(playerName)
            playerNameSema.release()
        }
    }

}
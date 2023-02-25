package de.mocoware.model

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

private val Context.dataStore by preferencesDataStore(name = "gameStatistics")

private const val PLAYERNAME_KEY = "playername"
const val PLAYERNAME_DEFAULT = "-/_undefined-/_"
private val PLAYERNAME = stringPreferencesKey(PLAYERNAME_KEY)

suspend fun getGlobalPlayerName(context: Context): String {
    val settings = context.dataStore.data.firstOrNull() ?: return PLAYERNAME_DEFAULT
    return settings[PLAYERNAME] ?: PLAYERNAME_DEFAULT
}

suspend fun setGlobalPlayername(context: Context, playername : String) {
    context.dataStore.edit { settings ->
        settings[PLAYERNAME] = playername
    }
}

object PlayedGamesDataStore {

    val mgWonCounterKeys = mutableListOf<Preferences.Key<Int>>()
    val mgPlayedCounterKeys = mutableListOf<Preferences.Key<Int>>()

    init {
        for (each in MiniGameEnum.values()){
            mgWonCounterKeys.add(intPreferencesKey(each.toString() + "_won"))
        }
        for (each in MiniGameEnum.values()){
            mgPlayedCounterKeys.add(intPreferencesKey(each.toString() + "_played"))
        }
    }

    fun gameEnd(context: Context, mgEnum: MiniGameEnum, won : Boolean){
        CoroutineScope(Dispatchers.Default).launch {
            if (won) incMGwonCount(context,mgEnum)
            incMGplayedCount(context,mgEnum)
        }
    }

    suspend fun getMGwonCount(context: Context, mgEnum: MiniGameEnum): Int {
        val settings = context.dataStore.data.firstOrNull() ?: return 0
        return settings[mgWonCounterKeys[mgEnum.ordinal]] ?: 0
    }

    suspend fun incMGwonCount(context: Context, mgEnum : MiniGameEnum){
        context.dataStore.edit { settings ->
            val currentCount = settings[mgWonCounterKeys[mgEnum.ordinal]] ?: 0
            settings[mgWonCounterKeys[mgEnum.ordinal]] = currentCount + 1
        }
    }

    suspend fun getMGplayedCount(context: Context, mgEnum: MiniGameEnum): Int {
        val settings = context.dataStore.data.firstOrNull() ?: return 0
        return settings[mgPlayedCounterKeys[mgEnum.ordinal]] ?: 0
    }

    suspend fun incMGplayedCount(context: Context, mgEnum : MiniGameEnum) {
        context.dataStore.edit { settings ->
            val currentCount = settings[mgPlayedCounterKeys[mgEnum.ordinal]] ?: 0
            settings[mgPlayedCounterKeys[mgEnum.ordinal]] = currentCount + 1
        }
    }
}

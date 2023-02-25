package de.mocoware.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.mocoware.model.PLAYERNAME_DEFAULT
import de.mocoware.model.getGlobalPlayerName
import de.mocoware.model.setGlobalPlayername
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserNameViewModel : ViewModel() {

    var nameLive = MutableLiveData(PLAYERNAME_DEFAULT)

    fun getPlayername(context: Context){
        GlobalScope.launch {
            nameLive.postValue(getGlobalPlayerName(context).toString())
        }
    }

    fun setAppPlayername(context: Context, name: String) {
        GlobalScope.launch {
            setGlobalPlayername(context, name)
        }
    }
}
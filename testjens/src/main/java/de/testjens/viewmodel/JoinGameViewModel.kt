package de.testjens.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.testjens.model.Game
import kotlin.random.Random

open class JoinGameViewModel : ViewModel() {

//    private val nextScreen by mutableState<String>

    var list = mutableListOf<Game>()

    private val _availableGames : MutableLiveData<List<Game>> = MutableLiveData<List<Game>>()
    val availableGames : LiveData<List<Game>> = _availableGames


    private val _test: MutableLiveData<Int> = MutableLiveData()
    val test: LiveData<Int> = _test

    fun addGame(newGame : Game){
        if (_availableGames.value == null){
            _availableGames.postValue(listOf(newGame))
        } else {
            val oldList = _availableGames.value
            val newList = oldList!!.plus(newGame)
            _availableGames.postValue(newList)
        }
    }

    fun updateList (){
        _availableGames.postValue(list.toList())
    }

    fun test (){
        _test.postValue(Random.nextInt(1,10))
    }

//    fun setNextScreen(path: String){
//        this.nextScreen = path
//    }

}
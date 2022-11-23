package de.testjens.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.testjens.model.Game
import de.testjens.model.Gamelist

open class AppViewModel : ViewModel() {

    var test by mutableStateOf(0)

    var availableGames = Gamelist()

}
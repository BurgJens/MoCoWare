package de.mocoware.viewmodel

import androidx.lifecycle.ViewModel
import de.mocoware.model.GameConnection

class ConnectionViewModel() : ViewModel() {

    fun searchExistingLobby(){
        GameConnection.startDiscovery()
    }

    fun createLobby(){

    }
}
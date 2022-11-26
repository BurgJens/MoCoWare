package com.example.testrobert.viewmodel

import androidx.lifecycle.ViewModel
import com.example.testrobert.model.GameRoom
import com.example.testrobert.model.User

class Playground():ViewModel(){

    var listeSpiele= mutableListOf<GameRoom>()

    var listeUser= mutableListOf<User>()



}

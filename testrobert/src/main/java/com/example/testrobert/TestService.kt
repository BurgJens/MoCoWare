package com.example.testrobert

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TestService:Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {

        while (1<5){
            println("hs")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
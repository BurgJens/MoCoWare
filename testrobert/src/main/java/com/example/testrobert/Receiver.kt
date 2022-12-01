package com.example.testrobert

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.testrobert.viewmodel.SpielViewModel
import java.util.*

class Receiver(val viewModel: SpielViewModel): BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val speed = Objects.requireNonNull(intent.extras)?.getDouble("speed")

        val axisX = Objects.requireNonNull(intent.extras)?.getFloat("axisX")
        val axisY = Objects.requireNonNull(intent.extras)?.getFloat("axisY")
        val axisZ = Objects.requireNonNull(intent.extras)?.getFloat("axisZ")

        if (speed != null) {
            viewModel.setSpeed(speed)
        }

        if(axisX != null && axisY!=null && axisZ != null){
            viewModel.setAcc(axisX,axisY,axisZ)
        }


    }
}
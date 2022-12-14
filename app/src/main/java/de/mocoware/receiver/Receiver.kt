package de.mocoware.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import de.mocoware.viewmodel.GameViewModel
import java.util.*

class Receiver(val viewModel: GameViewModel): BroadcastReceiver() {
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
package de.mocoware.model

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.atomic.AtomicBoolean

class MiniGameTimer(val timeToPlay : Int= 10) {

    var canStart = AtomicBoolean(true)

    val _isTimeUp : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var isTimeUp : LiveData<Boolean> = _isTimeUp

    val _time : MutableLiveData<Int> = MutableLiveData<Int>(timeToPlay)
    var time : LiveData<Int> = _time

    val timer = object : CountDownTimer(timeToPlay*1000.toLong(), 1000) {

        override fun onTick(millisUntilFinished: Long) {
            if(_time.value!! > 0)
                _time.postValue(time.value?.minus(1))
        }

        override fun onFinish() {
            _isTimeUp.postValue(true)
            canStart.set(true)
        }
    }

    fun tryStart():Boolean{
        if(canStart.get()) {
            canStart.set(false)
            timer.start()
            return true
        }else{
            return false
        }
    }
}
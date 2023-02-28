package de.mocoware.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.mocoware.model.*
import de.mocoware.model.minigames.*
import de.mocoware.sensor.*
import de.mocoware.util.*
import de.mocoware.view.navigation.NavMG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import java.util.*


class GameViewModel : ViewModel(){


    val highscores = listOf<HighScore>(/*HighScore("test","test",false,5)*/)

    var game = Game("Bla")

    var currentMG = game.getCurrentMG()

    var currentGameData = currentMG.gameData

    val wonGames = mutableListOf<Boolean>()

    var routeToMG = currentMG.gameRoute

    // Für Gyroskope start Orientierung
    var axisXGyro:Float= 0.0F
    var axisYGyro:Float= 0.0F
    var axisZGyro:Float= 0.0F

    // Maximale beschleunigungswerte
    var maxXwert=0.0f
    var maxYwert=0.0f
    var maxZwert=0.0f

    var serviceAccelIstAktiv=false
    var serviceSpeedIstAktiv=false
    var serviceLightIstAktiv=false
    var serviceGyrpIstAktiv=false

    var lichtWert=true

    private val _accel: MutableLiveData<Array<Float>> = MutableLiveData<Array<Float>>()
    var accel: LiveData<Array<Float>> = _accel

    private val _light: MutableLiveData<Float> = MutableLiveData<Float>()
    var light: LiveData<Float> = _light

    private val _speed : MutableLiveData<Double> = MutableLiveData<Double>()
    var speed : LiveData<Double> = _speed

    private val _gyro : MutableLiveData<Triple<Float,Float,Float>> = MutableLiveData<Triple<Float,Float,Float>>()
    var gyro : LiveData<Triple<Float,Float,Float>> = _gyro

    private val _gyroGrad : MutableLiveData<Triple<Double,Double,Double>> = MutableLiveData<Triple<Double,Double,Double>>()
    var gyroGrad : LiveData<Triple<Double,Double,Double>> = _gyroGrad

    val rotationVectorSema = Semaphore(1)
    private val _rotationVector : MutableLiveData<Triple<Float,Float,Float>> = MutableLiveData<Triple<Float,Float,Float>>()
    var rotationVector : LiveData<Triple<Float,Float,Float>> = _rotationVector


    // Nur wegen cast problem
    var gameDataMGannoyingButtons = MGannoyingButtons().gameData as DataMGannoyingButtons
    var gameDataMGlaufenWithService = MGlaufenWithService().gameData  as DataMGlaufenWithService
    var gameDataMGconfusingButtons = MGconfusingButtons().gameData as DataMGconfusingButtons
    var gameDataMGshake = MGshake().gameData as DataMGshake
    var gameDataMGbeleuchtung = MGbeleuchtung().gameData  as DataMGbeleuchtung
    var gameDataMGLoRButtonMasher = MGLoRButtonMasher().gameData as DataMGLoRButtonMasher
    var gameDataMGballInHole = MGballInHole().gameData as DataMGballInHole

    init {
        updateMGdata()
        updateAcceleration(1f,1f,1f)
        _light.postValue(0.1f)
    }

     // Receiver für die Services
    inner class Receiver: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val speed = Objects.requireNonNull(intent.extras)?.getDouble(SPEED_SENSOR_VALUE)

            val licht = Objects.requireNonNull(intent.extras)?.getFloat(LIGHT_SENSOR_VALUE)

            val axisX = Objects.requireNonNull(intent.extras)?.getFloat(ACCELERATION_SENSOR_X_VALUE)
            val axisY = Objects.requireNonNull(intent.extras)?.getFloat(ACCELERATION_SENSOR_Y_VALUE)
            val axisZ = Objects.requireNonNull(intent.extras)?.getFloat(ACCELERATION_SENSOR_Z_VALUE)

            val axisXGyroReciv = Objects.requireNonNull(intent.extras)?.getFloat(GYRO_SENSOR_X_VALUE)
            val axisYGyroReciv = Objects.requireNonNull(intent.extras)?.getFloat(GYRO_SENSOR_Y_VALUE)
            val axisZGyroreciv = Objects.requireNonNull(intent.extras)?.getFloat(GYRO_SENSOR_Z_VALUE)

            val roll = Objects.requireNonNull(intent.extras)?.getFloat(ROTATION_VECTOR_ROLL_VALUE)
            val pitch = Objects.requireNonNull(intent.extras)?.getFloat(ROTATION_VECTOR_PITCH_VALUE)
            val yaw = Objects.requireNonNull(intent.extras)?.getFloat(ROTATION_VECTOR_YAW_VALUE)

//            Log.d("checkSensors","RECIVER ")
//            Log.d("checkSensors","$roll   $pitch   $yaw")

            if( axisXGyroReciv != null && axisXGyroReciv != 0f &&
                axisYGyroReciv !=null && axisYGyroReciv != 0f &&
                axisZGyroreciv != null && axisZGyroreciv != 0f)
            {
                axisXGyro+=axisXGyroReciv

                axisYGyro+=axisYGyroReciv

                axisZGyro+=axisZGyroreciv
            }

            if (speed != null && speed != 0.toDouble()) {
                _speed.postValue(speed)
            }

            if (licht != null && licht != 0f) {
                if (_light.value==null) _light.value=licht
                if (licht > _light.value!!) _light.value=licht


            }
            if(
                axisX != null && axisX != 0f &&
                axisY!=null && axisY != 0f &&
                axisZ != null && axisZ != 0f
            ){
               updateAcceleration(axisX,axisY,axisZ)
            }

            if(
                axisXGyroReciv != null && axisXGyroReciv != 0f &&
                axisYGyroReciv!=null && axisYGyroReciv != 0f &&
                axisZGyroreciv != null && axisZGyroreciv != 0f
            ) {
                updateGyro(axisXGyroReciv, axisYGyroReciv, axisZGyroreciv)
            }

            if(
                roll != null && roll != 0f &&
                pitch!=null && pitch!=0f &&
                yaw != null && yaw != 0f
            ) {
                CoroutineScope(Dispatchers.Main).launch {
                    rotationVectorSema.acquire()
                    updateRotationVector(roll, pitch, yaw)
                    rotationVectorSema.release()
                }
            }
        }
    }

    fun updateMGdata(){
        when (currentMG.gameData){
            is DataMGannoyingButtons ->{
                gameDataMGannoyingButtons = currentMG.gameData as DataMGannoyingButtons
            }
            is DataMGlaufenWithService ->{
                gameDataMGlaufenWithService = currentMG.gameData as DataMGlaufenWithService
            }
            is DataMGconfusingButtons ->{
                gameDataMGconfusingButtons = currentMG.gameData as DataMGconfusingButtons
            }
            is DataMGshake ->{
                gameDataMGshake = currentMG.gameData as DataMGshake
            }
            is DataMGbeleuchtung ->{
                gameDataMGbeleuchtung = currentMG.gameData as DataMGbeleuchtung
            }
            is DataMGLoRButtonMasher ->{
                gameDataMGLoRButtonMasher = currentMG.gameData as DataMGLoRButtonMasher
            }
            is DataMGballInHole ->{
                gameDataMGballInHole = currentMG.gameData as DataMGballInHole
            }
        }
    }

    fun finishGame(context : Context, navigate : () -> Unit, won : Boolean = false){

        context.stopService(Intent(context, Accelerometer::class.java))
        context.stopService(Intent(context, Gyroskope::class.java))
        context.stopService(Intent(context, LightSensor::class.java))
        context.stopService(Intent(context, RotationVector::class.java))
        context.stopService(Intent(context, SpeedSensor::class.java))

        PlayedGamesDataStore.gameEnd(context, navMGrouteToMinigameEnum(routeToMG), won)
        ballInHoleCalcStop()

        wonGames.add(won)
        _light.postValue(0f)
        _accel.postValue(arrayOf(0.0f,0f,0f))

        val nextGame = game.nextGame()
        currentMG = game.getCurrentMG()
        currentGameData = currentMG.gameData

        if(nextGame){
            routeToMG = game.getRouteToMG()
        }else{
            routeToMG = NavMG.Lobby.route
        }

        updateMGdata()

        navigate()

        serviceAccelIstAktiv=false
        serviceSpeedIstAktiv=false
        serviceLightIstAktiv=false
        serviceGyrpIstAktiv=false
    }

    val timeToPlay = 10
    val timeToStart = 3

    var gameTimer = MiniGameTimer(timeToPlay)
    fun resetGameTimer(){
        gameTimer = MiniGameTimer(timeToPlay)
    }

    var gameStartTimer = MiniGameTimer(timeToStart)
    fun resetGameStartTimer(){
        gameStartTimer = MiniGameTimer(timeToStart)
    }

    fun updateAcceleration(floatX: Float, floatY: Float, floatZ: Float){
       if (floatX>maxXwert) {
           maxXwert=floatX
           _accel.postValue(arrayOf(maxXwert,maxYwert,maxZwert))
       }
       if (floatY>maxYwert) {
           maxYwert=floatY
           _accel.postValue(arrayOf(maxXwert,maxYwert,maxZwert))
       }
       if (floatZ>maxZwert) {
           maxZwert=floatZ
           _accel.postValue(arrayOf(maxXwert,maxYwert,maxZwert))
       }

//        Log.d("checkSensorsInViewModel","UPDATE ACCELERATION   $floatX   $floatY   $floatZ" )
    }

    fun updateGyro(floatX: Float, floatY: Float, floatZ: Float){
        val oldVal = _gyro.value
        val newVal = Triple(
            (oldVal?.first ?: 0f) +floatX,
            (oldVal?.second ?: 0f) +floatY,
            (oldVal?.third ?: 0f) +floatZ
        )

        val newValDegree = Triple(
            Math.toDegrees(newVal.first.toDouble()),
            Math.toDegrees(newVal.second.toDouble()),
            Math.toDegrees(newVal.third.toDouble())
        )

        _gyro.postValue(newVal)
        _gyroGrad.postValue(newValDegree)

//        Log.d("checkSensorsInViewModel","UPDATE GYRO $newValDegree")
    }

    fun updateRotationVector(roll: Float, pitch: Float, yaw: Float){
        val newVal= Triple(roll,pitch,yaw)

//        Log.d("checkSensorsInViewModel","UPDATE ROTATION VECTOR $newVal")

        _rotationVector.postValue(newVal)
    }

    val ballInHoleSema = Semaphore(1)
    val ballPos = MutableLiveData(Pair(0.dp,0.dp))

    var lastDraw = System.currentTimeMillis()

    val speedMult = 0.05
    val borderX = 100.dp
    val borderY = 100.dp

    fun ballInHoleCalcStart(){
        CoroutineScope(Dispatchers.Default).launch{
            if (ballInHoleSema.tryAcquire()){
                lastDraw = System.currentTimeMillis()

                while (ballInHoleSema.availablePermits == 0){
                    val currentTime = System.currentTimeMillis()
                    val deltaTime = currentTime - lastDraw
                    lastDraw = currentTime

                    var newX = (ballPos.value?.first ?: 0.dp)
                    var newY = (ballPos.value?.second  ?: 0.dp)

                    rotationVectorSema.acquire()
                    val rotationVectorFirst = (rotationVector.value?.first ?: 0f)
                    val rotationVectorSecond = (rotationVector.value?.second ?: 0f)
                    rotationVectorSema.release()

                    val translateX = rotationVectorFirst * speedMult * deltaTime.toFloat()
                    val translateY = rotationVectorSecond * speedMult * deltaTime.toFloat()

                    if (rotationVectorFirst > 10 || rotationVectorFirst < -10){
                        if (newX > borderX){
                            newX = borderX
                        }else if (newX < -borderX){
                            newX = -borderX
                        }else {
                            newX += translateX.dp
                        }
                    }
                    if (rotationVectorSecond > 10 || rotationVectorSecond < -10){
                        if (newY > borderY){
                            newY = borderY
                        }else if (newY < -borderY){
                            newY = -borderY
                        }else {
                            newY += translateY.dp
                        }
                    }
                    ballPos.postValue(Pair(newX,newY))
                }
                ballInHoleSema.acquire()
                ballPos.postValue(Pair(0.dp,0.dp))
                ballInHoleSema.release()
            }
        }
    }

    fun ballInHoleCalcStop(){
        if (ballInHoleSema.availablePermits == 0)ballInHoleSema.release()
    }
}


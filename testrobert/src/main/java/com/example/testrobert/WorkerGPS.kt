package com.example.testrobert

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkerGPS(context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {

    override fun doWork(): Result {
        var i =1
        while (i<1){
            println("test")
        }



        return Result.retry()
    }

}
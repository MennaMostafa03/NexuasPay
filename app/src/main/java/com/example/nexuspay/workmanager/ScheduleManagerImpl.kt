package com.example.nexuspay.workmanager

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.util.concurrent.TimeUnit

class ScheduleManagerImpl(
   private val context: Context
) : ScheduleManager {
    override fun scheduleRetry(id : Int) {

        val workRequest = OneTimeWorkRequestBuilder<TransactionWorker>()
            .setInputData(
                workDataOf(
                    "transactionId" to id
                )
            )
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(context)
            .enqueue(workRequest)

    }
}
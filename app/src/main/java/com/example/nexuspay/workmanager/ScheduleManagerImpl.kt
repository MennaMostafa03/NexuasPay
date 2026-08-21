package com.example.nexuspay.workmanager

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class ScheduleManagerImpl(
   private val context: Context
) : ScheduleManager {
    override fun scheduleRetry() {

        val workRequest = PeriodicWorkRequestBuilder<TransactionWorker>(
            15,
            TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "TransactionRetry",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
    }
}
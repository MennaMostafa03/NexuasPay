package com.example.nexuspay.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.nexuspay.domain.usecase.transaction.RetrySendMoneyUseCase

class TransactionWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    val retryUseCase: RetrySendMoneyUseCase
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {

        return try {
            retryUseCase.invoke()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
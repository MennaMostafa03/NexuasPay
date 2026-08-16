package com.example.nexuspay.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.nexuspay.domain.usecase.RetrySendMoneyUseCase
import com.example.nexuspay.utils.exception.TransactionResult

class TransactionWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    val retryUseCase: RetrySendMoneyUseCase
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {

        val id = inputData.getInt("transactionId", -1)

        if (id == -1) {
            return Result.failure()
        }

        val result = retryUseCase.invoke(id)

        return when (result) {
            TransactionResult.Success -> Result.success()
            TransactionResult.Pending -> Result.retry()
            TransactionResult.Failed -> Result.failure()
        }
    }
}
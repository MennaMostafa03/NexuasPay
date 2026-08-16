package com.example.nexuspay

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.work.Configuration
import androidx.work.WorkManager

import androidx.work.WorkerFactory
import com.example.nexuspay.data.di.nexusPayModule
import com.example.nexuspay.data.di.dataStoreModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.factory.KoinWorkerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class MyApplication : Application(), Configuration.Provider {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(nexusPayModule,dataStoreModule)
        }
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(KoinWorkerFactory())
            .build()
}

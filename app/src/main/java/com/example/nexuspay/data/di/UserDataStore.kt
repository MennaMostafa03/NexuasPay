package com.example.nexuspay.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.okio.OkioStorage
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferencesSerializer
import androidx.datastore.preferences.core.stringPreferencesKey
import okio.FileSystem
import okio.Path.Companion.toPath
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

internal const val dataStoreFileName = "user_preference"

object UserPreferencesKeys {
    val IDENTIFIER = stringPreferencesKey("identifier")
    val USER_NAME = stringPreferencesKey("user_name")
    val PROFILE_IMAGE = stringPreferencesKey("profile_image")
    val BALANCE = stringPreferencesKey("balance")
    val CURRENCY = stringPreferencesKey("currency")
}


val dataStoreModule: Module = module {
    single <DataStore<Preferences>> {
        DataStoreFactory.create(
            storage = OkioStorage(
                fileSystem = FileSystem.SYSTEM,
                serializer = PreferencesSerializer,
                producePath = {
                    androidContext().filesDir.resolve(dataStoreFileName).absolutePath.toPath()
                }
            )
        )
    }
}
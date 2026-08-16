package com.example.nexuspay.data.local_ds.user

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.nexuspay.data.di.UserPreferencesKeys
import com.example.nexuspay.domain.model.response.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserLocalDataImpl (
    val dataStore: DataStore<Preferences>
): UserLocalData {


    override suspend fun saveUser(user: UserResponse) {
        dataStore.edit { preferences ->
            preferences[UserPreferencesKeys.IDENTIFIER] = user.identifier.toString()
            preferences[UserPreferencesKeys.USER_NAME] = user.name.toString()
            preferences[UserPreferencesKeys.PROFILE_IMAGE] = user.avatar.toString()
            preferences[UserPreferencesKeys.BALANCE] = user.balance.toString()
            preferences[UserPreferencesKeys.CURRENCY] = user.currency.toString()

        }
    }

    override fun getUser(): Flow<UserResponse> =
        dataStore.data.map { preferences ->
            UserResponse(
                identifier = preferences[UserPreferencesKeys.IDENTIFIER],
                name = preferences[UserPreferencesKeys.USER_NAME],
                avatar = preferences[UserPreferencesKeys.PROFILE_IMAGE],
                balance = preferences[UserPreferencesKeys.BALANCE]?.toIntOrNull(),
                currency = preferences[UserPreferencesKeys.CURRENCY]
            )
        }
}
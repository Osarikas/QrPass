package ru.myitschool.work.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_login")
class UserDataStoreManager(private val context: Context) {

    companion object {
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val PASSWORD_KEY = stringPreferencesKey("password")

        fun getInstance(context: Context): UserDataStoreManager {
            return UserDataStoreManager(context.applicationContext)
        }
    }

    val usernameFlow: Flow<String> = context.applicationContext.dataStore.data.map { prefs ->
        prefs[USERNAME_KEY] ?: ""
    }
    val passwordFlow: Flow<String> = context.applicationContext.dataStore.data.map { prefs ->
        prefs[PASSWORD_KEY] ?: ""
    }
    suspend fun saveCredentials(username: String, password: String) {
        context.dataStore.edit { prefs ->
            prefs[USERNAME_KEY] = username
            prefs[PASSWORD_KEY] = password
        }
    }
    suspend fun clearCredentials() {
        context.applicationContext.dataStore.edit { it.clear() }
    }
}
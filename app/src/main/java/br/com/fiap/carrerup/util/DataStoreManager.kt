package br.com.fiap.carrerup.util

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

const val USER_DATASTORE = "user_data"

val Context.preferenceDataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATASTORE)

class DataStoreManager(val context: Context) {

    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("IS_LOGGED_IN")
    }

    suspend fun login() {
        context.preferenceDataStore.edit {
            it[IS_LOGGED_IN] = true
            Log.i("DSM", "LOGIN: ${IS_LOGGED_IN.toString()}")
        }
    }

    suspend fun logout() {
        context.preferenceDataStore.edit {
            it[IS_LOGGED_IN] = false
            Log.i("DSM", "LOGOUT: ${IS_LOGGED_IN.toString()}")
        }
    }

    fun getIsLoggedIn() = context.preferenceDataStore.data.map {
        it[IS_LOGGED_IN] ?: false
    }

}
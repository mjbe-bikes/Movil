
package com.example.mjbebikesaplicativomovil.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

// Crea el almacenamiento local de preferencias para guardar el token.
private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class TokenManager(private val context: Context) {

    companion object {
        // Clave con la que se identifica el token guardado.
        private val TOKEN_KEY = stringPreferencesKey("auth_token")
    }

    // Guarda el token recibido desde Laravel.
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    // Permite observar el token almacenado.
    val token: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[TOKEN_KEY]
        }

    // Obtiene el token actual una sola vez.
    suspend fun getToken(): String? {
        return token.first()
    }

    // Elimina el token cuando el usuario cierra sesión.
    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}

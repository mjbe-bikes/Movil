package com.example.mjbebikesaplicativomovil.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mjbebikesaplicativomovil.data.local.TokenManager
import com.example.mjbebikesaplicativomovil.remote.RetrofitClient
import com.example.mjbebikesaplicativomovil.data.repository.AuthRepository

class LoginViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {

            // Crea el servicio de comunicación con Laravel.
            val apiService = RetrofitClient.create(context)

            // Crea el administrador del token.
            val tokenManager = TokenManager(context.applicationContext)

            // Crea el repositorio de autenticación.
            val repository = AuthRepository(
                apiService,
                tokenManager
            )

            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }

        throw IllegalArgumentException("ViewModel desconocido")
    }
}

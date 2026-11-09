package com.example.mjbebikesaplicativomovil.data.repository

import com.example.mjbebikesaplicativomovil.data.local.TokenManager
import com.example.mjbebikesaplicativomovil.remote.ApiService
import com.example.mjbebikesaplicativomovil.remote.models.LoginRequest
import com.example.mjbebikesaplicativomovil.remote.models.LoginResponse
import retrofit2.Response

class AuthRepository(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) {

    // Inicia sesión utilizando las credenciales del usuario.
    suspend fun login(
        email: String,
        passwordHash: String
    ): Response<LoginResponse> {

        val response = apiService.login(
            LoginRequest(
                email = email,
                passwordHarsh = passwordHash
            )
        )

        // Si Laravel responde correctamente, guarda el JWT.
        if (response.isSuccessful) {
            response.body()?.token?.let { token ->
                tokenManager.saveToken(token)
            }
        }

        return response
    }
}

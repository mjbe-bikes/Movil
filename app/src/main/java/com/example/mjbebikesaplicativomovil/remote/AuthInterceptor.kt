package com.example.mjbebikesaplicativomovil.remote

import com.example.mjbebikesaplicativomovil.data.local.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        // Recupera el JWT almacenado localmente.
        val token = runBlocking {
            tokenManager.getToken()
        }

        // Construye la petición original.
        val request = chain.request().newBuilder()

        // Si existe un token, lo agrega a la cabecera Authorization.
        if (!token.isNullOrBlank()) {
            request.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(request.build())
    }
}

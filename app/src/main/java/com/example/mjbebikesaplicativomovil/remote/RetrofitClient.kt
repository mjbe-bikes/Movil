
package com.example.mjbebikesaplicativomovil.remote

import android.content.Context
import com.example.mjbebikesaplicativomovil.data.local.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // Dirección de Laravel desde el emulador Android.
    private const val BASE_URL = "http://10.0.2.2:8000/api/"

    // Crea la conexión con Laravel.
    fun create(context: Context): ApiService {

        // Permite acceder al almacenamiento del token.
        val tokenManager = TokenManager(context.applicationContext)

        // Configura OkHttp para agregar el JWT a las peticiones.
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenManager))
            .build()

        // Configura Retrofit y devuelve el servicio API.
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

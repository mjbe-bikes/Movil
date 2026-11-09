package com.example.mjbebikesaplicativomovil.remote.models
import com.google.gson.annotations.SerializedName
data class LoginResponse(
    val mensaje: String,
    val token: String,
    val usuario: UsuarioLogin
)

data class UsuarioLogin(
    val id: Int,
    val login: String,
    val email: String,
    @SerializedName("rol_id")
    val rolid: Int
)

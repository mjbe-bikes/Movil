package com.example.mjbebikesaplicativomovil.remote.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val email: String,

    @SerializedName("password_harsh")
    val passwordHarsh: String
)

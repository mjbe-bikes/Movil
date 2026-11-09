package com.example.mjbebikesaplicativomovil.utils

import java.security.MessageDigest

object PasswordUtils {

    // Convierte la contraseña en SHA-256.
    fun sha256(password: String): String {

        val bytes = MessageDigest
            .getInstance("SHA-256")
            .digest(password.toByteArray(Charsets.UTF_8))

        // Convierte cada byte en dos caracteres hexadecimales.
        return bytes.joinToString("") { byte ->
            "%02x".format(byte.toInt() and 0xff)
        }
    }
}

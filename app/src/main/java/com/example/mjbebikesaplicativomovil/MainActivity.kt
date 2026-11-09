
package com.example.mjbebikesaplicativomovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mjbebikesaplicativomovil.ui.theme.MjbebikesAplicativoMovilTheme
import com.example.mjbebikesaplicativomovil.viewmodel.LoginViewModel
import com.example.mjbebikesaplicativomovil.viewmodel.LoginViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MjbebikesAplicativoMovilTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {

    // Obtenemos el contexto de Android.
    val context = LocalContext.current

    // Creamos el ViewModel con su fábrica.
    val loginViewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(context)
    )

    // Variables para almacenar lo que escribe el usuario.
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Observamos los estados del inicio de sesión.
    val isLoading by loginViewModel.isLoading.collectAsState()
    val errorMessage by loginViewModel.errorMessage.collectAsState()
    val loginSuccess by loginViewModel.loginSuccess.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Nombre provisional de la aplicación.
            Text(
                text = "Mjbebikes Aplicativo Movil",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Campo para escribir el correo.
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo para escribir la contraseña.
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón que ejecuta el inicio de sesión.
            Button(
                onClick = {
                    loginViewModel.login(email.trim(), password)
                },
                enabled = !isLoading &&
                        email.isNotBlank() &&
                        password.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {

                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text("Iniciar sesión")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mostramos el resultado de la autenticación.
            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error
                )
            }

            if (loginSuccess) {
                Text(
                    text = "¡Inicio de sesión correcto!",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

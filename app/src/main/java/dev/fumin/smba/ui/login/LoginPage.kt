package dev.fumin.smba.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay

@Composable
fun LoginPage(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSucceeded: () -> Unit
) {

    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val state = viewModel.state.observeAsState(LoginPageState.Idle)

    if (state.value is LoginPageState.LoginSucceeded) {
        LaunchedEffect(state.value) {
            delay(500)
            onLoginSucceeded()
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 64.dp, horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "LOGIN",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color(0xFF1A1C1E)
            )
            Box(
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (state.value is LoginPageState.LoginFailed) {
                    Text(
                        text = "ログインIDかパスワードが違います",
                        fontSize = 16.sp,
                        color = Color(0xFFE13B3B),
                    )
                }
            }
            TextField(
                value = id,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                ),
                onValueChange = { id = it },
                label = {
                    Text(text = "ログインID")
                },
                enabled = when (state.value) {
                    is LoginPageState.Authenticating, is LoginPageState.LoginSucceeded -> false
                    else -> true
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(32.dp))
            TextField(
                value = password,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                onValueChange = { password = it },
                label = {
                    Text(text = "パスワード")
                },
                enabled = when (state.value) {
                    is LoginPageState.Authenticating, is LoginPageState.LoginSucceeded -> false
                    else -> true
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(64.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.login(id, password) }) {
                when (state.value) {
                    is LoginPageState.Authenticating -> CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )

                    is LoginPageState.LoginSucceeded -> Icon(
                        Icons.Rounded.Done, contentDescription = null
                    )

                    else -> Text(text = "ログイン")
                }
            }
        }
    }
}

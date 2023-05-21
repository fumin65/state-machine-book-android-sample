package dev.fumin.smba.ui.login

sealed class LoginPageState {
    object Idle : LoginPageState()
    object Authenticating : LoginPageState()
    object LoginFailed : LoginPageState()
    object LoginSucceeded : LoginPageState()
}

package dev.fumin.smba.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fumin.smba.api.AuthApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authApi: AuthApi
) : ViewModel() {

    private val _state = MutableLiveData<LoginPageState>()
    val state: LiveData<LoginPageState> = _state

    fun login(id: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value is LoginPageState.Authenticating) {
                return@launch
            }
            _state.postValue(LoginPageState.Authenticating)
            val succeeded = authApi.authenticate(id, password)
            val newState = if (succeeded) {
                LoginPageState.LoginSucceeded
            } else {
                LoginPageState.LoginFailed
            }
            _state.postValue(newState)
        }
    }

}

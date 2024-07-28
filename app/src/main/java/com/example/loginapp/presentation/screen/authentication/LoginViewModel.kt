package com.example.loginapp.presentation.screen.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp.domain.common.DataState
import com.example.loginapp.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<DataState<*>?>(null)
    val loginState: StateFlow<DataState<*>?> get() = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            repository.login(email, password).collect { state ->
                _loginState.value = state
            }
        }
    }
}

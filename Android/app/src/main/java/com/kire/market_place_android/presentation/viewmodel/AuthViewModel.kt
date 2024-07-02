package com.kire.market_place_android.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kire.market_place_android.domain.model.auth.AuthResultDomain

import com.kire.market_place_android.domain.use_case.common.util.IAuthUseCases
import com.kire.market_place_android.presentation.model.auth.AuthState
import com.kire.market_place_android.presentation.model.auth.AuthUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.receiveAsFlow

import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: IAuthUseCases
): ViewModel() {

    var authState by mutableStateOf(AuthState())

    private val _authResultDomainChannel = Channel<AuthResultDomain<List<String?>>>()
    val authResultChannel = _authResultDomainChannel.receiveAsFlow()

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    fun onEvent(event: AuthUiEvent) {
        when(event) {
            is AuthUiEvent.LogInPhoneChanged -> {
                authState = authState.copy(logInPhone = event.value)
            }
            is AuthUiEvent.LogInPasswordChanged -> {
                authState = authState.copy(logInPassword = event.value)
            }
            AuthUiEvent.LogIn -> logIn()

            is AuthUiEvent.LogOnNameChanged -> {
                authState = authState.copy(logOnName = event.value)
            }
            is AuthUiEvent.LogOnPhoneChanged -> {
                authState = authState.copy(logOnPhone = event.value)
            }
            is AuthUiEvent.LogOnEmailChanged -> {
                authState = authState.copy(logOnEmail = event.value)
            }
            is AuthUiEvent.LogOnPasswordChanged -> {
                authState = authState.copy(logOnPassword = event.value)
            }
            is AuthUiEvent.LogOnRepeatedPasswordChanged -> {
                authState = authState.copy(logOnRepeatedPassword = event.value)
            }
            AuthUiEvent.LogOn -> logOn()
        }
    }

    suspend fun isTokenExpired() =
            authUseCases.isTokenExpiredUseCase()



    private fun logOn() {
        viewModelScope.launch {

            authState = authState.copy(isLoading = true)

            val result = authUseCases.logOnUseCase(
                username = authState.logOnName,
                phone = authState.logOnPhone,
                email = authState.logOnEmail,
                password = authState.logOnPassword
            )
            _authResultDomainChannel.send(result)
            authState = authState.copy(isLoading = false)
        }
    }

    private fun logIn() {
        viewModelScope.launch {

            authState = authState.copy(isLoading = true)

            val result = authUseCases.logInUseCase(
                phone = authState.logInPhone,
                password = authState.logInPassword
            )
            _authResultDomainChannel.send(result)
            authState = authState.copy(isLoading = false)
        }
    }

    init {
        viewModelScope.launch {
            authUseCases.isAuthenticatedUseCase().collect {
                _isAuthenticated.value = it
            }
        }
    }
}
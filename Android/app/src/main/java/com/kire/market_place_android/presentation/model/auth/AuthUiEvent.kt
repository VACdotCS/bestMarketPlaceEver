package com.kire.market_place_android.presentation.model.auth

sealed class AuthUiEvent {
    data class LogOnNameChanged(val value: String): AuthUiEvent()
    data class LogOnPhoneChanged(val value: String): AuthUiEvent()
    data class LogOnEmailChanged(val value: String): AuthUiEvent()
    data class LogOnPasswordChanged(val value: String): AuthUiEvent()
    data class LogOnRepeatedPasswordChanged(val value: String): AuthUiEvent()
    object LogOn: AuthUiEvent()

    data class LogInPhoneChanged(val value: String): AuthUiEvent()
    data class LogInPasswordChanged(val value: String): AuthUiEvent()
    object LogIn: AuthUiEvent()
}
package com.kire.market_place_android.presentation.model.auth

data class AuthState(
    val isLoading: Boolean = false,
    val logInPhone: String = "",
    val logInPassword: String = "",
    val logOnName: String = "",
    val logOnPhone: String = "",
    val logOnEmail: String = "",
    val logOnPassword: String = "",
    val logOnRepeatedPassword: String = "",
)

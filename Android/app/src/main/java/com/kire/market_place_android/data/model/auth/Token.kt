package com.kire.market_place_android.data.model.auth

data class Token(
    val token: String = "",
    val role: Role = Role.USER,
    val userId: Int = 0
)

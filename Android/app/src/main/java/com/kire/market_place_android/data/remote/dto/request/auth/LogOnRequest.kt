package com.kire.market_place_android.data.remote.dto.request.auth

import kotlinx.serialization.Serializable

@Serializable
data class LogOnRequest(
    val username: String,
    val password: String,
    val phone: String,
    val email: String
)

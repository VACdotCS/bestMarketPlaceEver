package com.kire.market_place_android.data.remote.dto.request.auth

import kotlinx.serialization.Serializable

@Serializable
data class LogInRequest(
    val phone: String,
    val password: String
)

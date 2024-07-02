package com.kire.market_place_android.data.remote.dto.response.auth

import com.kire.market_place_android.data.model.auth.Role
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val token: String = "",
    @SerialName("role") val role: Role = Role.USER,
    @SerialName("user_id") val userId: Int = 0
)

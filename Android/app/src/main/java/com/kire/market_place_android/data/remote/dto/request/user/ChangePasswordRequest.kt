package com.kire.market_place_android.data.remote.dto.request.user

import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordRequest(
    val currentPassword: String = "",
    val newPassword: String = "",
    val confirmationPassword: String = ""
)

package com.kire.market_place_android.data.remote.dto.request.user

import kotlinx.serialization.Serializable

@Serializable
data class ChangeUserInfoRequest(
    val username: String = "",
    val phone: String = "",
    val email: String = ""
)

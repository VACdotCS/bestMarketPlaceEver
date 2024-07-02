package com.kire.market_place_android.data.remote.dto.request.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangeUserCardRequest(
    @SerialName("card_number")
    val cardNumber: String = "",
    val CVC: String = "",
    val validity: String = ""
)

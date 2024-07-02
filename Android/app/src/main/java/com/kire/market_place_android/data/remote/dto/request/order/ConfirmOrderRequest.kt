package com.kire.market_place_android.data.remote.dto.request.order

import kotlinx.serialization.Serializable

@Serializable
data class ConfirmOrderRequest(
    val received: List<Int>,
    val returned: List<Int>
)
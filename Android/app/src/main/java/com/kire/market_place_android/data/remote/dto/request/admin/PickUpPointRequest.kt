package com.kire.market_place_android.data.remote.dto.request.admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PickUpPointRequest(
    @SerialName("manager_id")
    val managerId: Int = 0,
    val address: String = ""
)

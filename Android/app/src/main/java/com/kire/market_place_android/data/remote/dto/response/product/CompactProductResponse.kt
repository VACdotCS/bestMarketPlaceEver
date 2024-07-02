package com.kire.market_place_android.data.remote.dto.response.product

import kotlinx.serialization.Serializable

@Serializable
data class CompactProductResponse(
    val id: Int = 0,
    val title: String = "",
    val image: String = "",
    val unit: String = ""
)
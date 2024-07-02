package com.kire.market_place_android.data.remote.dto.response.product

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val categoryId: Int = 0,
    val title: String = ""
)

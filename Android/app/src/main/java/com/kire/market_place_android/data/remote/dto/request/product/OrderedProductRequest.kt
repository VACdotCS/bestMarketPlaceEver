package com.kire.market_place_android.data.remote.dto.request.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderedProductRequest(
    @SerialName("product_id")
    val productId: Int,
    @SerialName("count")
    val countProduct: Int
)
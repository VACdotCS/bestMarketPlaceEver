package com.kire.market_place_android.data.remote.dto.response.product

import com.kire.market_place_android.data.remote.dto.response.util.serializer.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class ProductResponse(
    val id: Int = 0,
    val title: String = "",
    @SerialName("image")
    val image: String = "",
    val description: String = "",
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal = 0.0.toBigDecimal(),
    @Serializable(with = BigDecimalSerializer::class)
    @SerialName("discount_price")
    val discountPrice: BigDecimal = 0.0.toBigDecimal(),
    @SerialName("quantity_available")
    val quantityAvailable: Int = 0,
    val unit: String = "",
    @SerialName("delivery_days")
    val deliveryDays: Int = 0,
    @SerialName("categories")
    val category: String = ""
)

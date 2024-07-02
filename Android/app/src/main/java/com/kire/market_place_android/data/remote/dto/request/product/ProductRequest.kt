package com.kire.market_place_android.data.remote.dto.request.product

import com.kire.market_place_android.data.remote.dto.response.util.serializer.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class ProductRequest(
    val title: String = "",
    val image: ByteArray = byteArrayOf(),
    val description: String = "",
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal = 0.0.toBigDecimal(),
    @SerialName("discount_price")
    @Serializable(with = BigDecimalSerializer::class)
    val discountPrice: BigDecimal = 0.0.toBigDecimal(),
    @SerialName("quantity_of_available")
    val quantityOfAvailable: Int = 0,
    val unit: String = "",
    @SerialName("delivery_days")
    val deliveryDays: Int = 0,
    @SerialName("category")
    val category: String = "",
)

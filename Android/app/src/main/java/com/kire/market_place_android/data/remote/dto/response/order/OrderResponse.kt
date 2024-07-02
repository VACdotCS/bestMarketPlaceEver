package com.kire.market_place_android.data.remote.dto.response.order

import com.kire.market_place_android.data.remote.dto.response.util.serializer.LocalDateSerializer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class OrderResponse(
    @SerialName("order_id")
    val orderId: Int = 0,
    val products: List<OrderedProductResponse> = emptyList(),
    @Serializable(with = LocalDateSerializer::class)
    val datetime: LocalDate? = LocalDate.parse("2018-12-12"),
    val completed: Boolean = false
)
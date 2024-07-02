package com.kire.market_place_android.data.remote.dto.request.order

import com.kire.market_place_android.data.remote.dto.request.product.OrderedProductRequest
import com.kire.market_place_android.data.remote.dto.response.order.OrderedProductResponse
import com.kire.market_place_android.data.remote.dto.response.util.serializer.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class OrderRequest(
    @SerialName("pickup_point_id")
    val pickUpPointId: Int = 0,
    @SerialName("products")
    val orderedProducts: List<OrderedProductRequest> = emptyList(),
    @Serializable(with = BigDecimalSerializer::class)
    @SerialName("order_price")
    val orderPrice: BigDecimal = 0.0.toBigDecimal()
)
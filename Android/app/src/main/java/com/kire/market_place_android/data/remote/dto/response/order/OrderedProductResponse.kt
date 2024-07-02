package com.kire.market_place_android.data.remote.dto.response.order

import com.kire.market_place_android.data.remote.dto.response.product.CompactProductResponse
import com.kire.market_place_android.data.remote.dto.response.util.serializer.BigDecimalSerializer
import com.kire.market_place_android.data.remote.dto.response.util.serializer.LocalDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDate

@Serializable
data class OrderedProductResponse(
    val quantity: Int = 0,
    @SerialName("ordered_product")
    val product: CompactProductResponse = CompactProductResponse(),
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal = 0.0.toBigDecimal(),
    @SerialName("delivery_days")
    val deliveryDays: Int = 0,
    @SerialName("delivery_status")
    val deliveryStatus: String = "",
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("completion_date")
    val completionDate: LocalDate? = null
)

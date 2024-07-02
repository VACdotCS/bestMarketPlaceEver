package com.kire.market_place_android.data.remote.dto.response.user

import com.kire.market_place_android.data.remote.dto.response.util.serializer.BigDecimalSerializer
import com.kire.market_place_android.data.remote.dto.response.util.serializer.LocalDateSerializer
import com.kire.market_place_android.data.remote.dto.response.util.serializer.NaNDoubleStringSerializer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import java.math.BigDecimal
import java.time.LocalDate

@Serializable
data class UserInfoResponse(
    val username: String = "",
    val email: String = "",
    val phone: String = "",
    @SerialName("card_number")
    val cardNumber: String? = "",
    @SerialName("cvc")
    val CVC: Int? = 0,
    @Serializable(with = LocalDateSerializer::class)
    val validity: LocalDate? = LocalDate.parse("2023-01-02"),
    @SerialName("user_discount")
    val userDiscount: Double? = 0.0,
    @Serializable(with = BigDecimalSerializer::class)
    @SerialName("amount_spent")
    val amountSpent: BigDecimal? = 0.0.toBigDecimal(),
    @SerialName("order_count")
    val orderCount: Int = 0,
    @Serializable(with = NaNDoubleStringSerializer::class)
    @SerialName("redemption_percent")
    val redemptionPercent: Double? = null
)

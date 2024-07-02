package com.kire.market_place_android.data.remote.dto.response.admin

import com.kire.market_place_android.data.model.auth.Role
import com.kire.market_place_android.data.remote.dto.response.util.serializer.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class AdminUserInfoResponse(
    val id: Int = 0,
    val username: String = "",
    val role: Role = Role.USER,
    @Serializable(with = BigDecimalSerializer::class)
    @SerialName("amount_spent")
    val amountSpent: BigDecimal? = null,
    @SerialName("user_discount")
    val userDiscount: Double? = null
)

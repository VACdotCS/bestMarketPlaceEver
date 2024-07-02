package com.kire.market_place_android.presentation.model.admin

import com.kire.market_place_android.presentation.model.user.Role
import java.math.BigDecimal

data class AdminUserInfo(
    val id: Int = 0,
    val username: String = "",
    val role: Role = Role.USER,
    val amountSpent: BigDecimal? = null,
    val userDiscount: Double? = null
)

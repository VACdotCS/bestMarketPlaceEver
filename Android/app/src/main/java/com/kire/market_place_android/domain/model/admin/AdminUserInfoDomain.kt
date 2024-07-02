package com.kire.market_place_android.domain.model.admin

import com.kire.market_place_android.domain.model.user.RoleDomain
import java.math.BigDecimal

data class AdminUserInfoDomain(
    val id: Int = 0,
    val username: String = "",
    val role: RoleDomain = RoleDomain.USER,
    val amountSpent: BigDecimal? = null,
    val userDiscount: Double? = null
)

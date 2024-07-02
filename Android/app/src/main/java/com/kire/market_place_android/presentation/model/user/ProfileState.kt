package com.kire.market_place_android.presentation.model.user

import java.math.BigDecimal

data class ProfileState(
    val username: String = "",
    val phone: String = "",
    val email: String = "",
    val cardNumber: String = "",
    val CVC: String = "",
    val validity: String = "",
    val currentPassword: String = "",
    val newPassword: String = "",
    val confirmationPassword: String = "",
    val userDiscount: Double? = null,
    val redemptionPercent: Double? = null,
    val amountSpent: BigDecimal? = null
)

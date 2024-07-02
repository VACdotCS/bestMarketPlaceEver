package com.kire.market_place_android.presentation.model.user

import java.math.BigDecimal
import java.time.LocalDate

/**
 * By Aleksey Timko (de4ltt)*/
data class User(
    val username: String = "",
    val userDiscount: Double? = 0.0,
    val amountSpent: BigDecimal? = 0.0.toBigDecimal(),
    val email: String = "",
    val phone: String = "",
    val cardNumber: String? = "",
    val CVC: Int? = 0,
    val validity: LocalDate? = LocalDate.parse("2018-12-12"),
    val orderCount: Int = 0,
    val redemptionPercent: Double? = 0.0
)
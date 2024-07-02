package com.kire.market_place_android.data.model.user

import java.math.BigDecimal
import java.time.LocalDate

/**
 * By Aleksey Timko (de4ltt)*/
data class UserEntity(
    val username: String,
    val userDiscount: Double,
    val amountSpent: BigDecimal,
    val email: String,
    val phone: String,
    val cardNumber: String,
    val CVC: Int,
    val validity: LocalDate,
    val orderCount: Int,
    val redemptionPercent: Double
)
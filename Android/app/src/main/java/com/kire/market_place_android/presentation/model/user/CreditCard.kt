package com.kire.market_place_android.presentation.model.user

import java.time.LocalDate

/**
 * By Michael Gontarev (KiREHwYE))*/
data class CreditCard(
    val number: String,
    val date: LocalDate,
    val cvc: Int
)
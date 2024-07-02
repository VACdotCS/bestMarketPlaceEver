package com.kire.market_place_android.domain.model.user

import java.time.LocalDate

/**
 * By Aleksey Timko (de4ltt)*/
data class CreditCardDomain(
    val number: String,
    val date: LocalDate,
    val cvc: Int
)
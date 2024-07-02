package com.kire.market_place_android.data.model.user

import java.time.LocalDate

/**
 * By Aleksey Timko (de4ltt)*/
data class CreditCardEntity(
    val number: String,
    val date: LocalDate,
    val cvc: Int
)
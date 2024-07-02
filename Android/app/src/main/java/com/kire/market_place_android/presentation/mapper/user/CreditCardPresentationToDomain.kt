package com.kire.market_place_android.presentation.mapper.user

import com.kire.market_place_android.domain.model.user.CreditCardDomain
import com.kire.market_place_android.presentation.model.user.CreditCard

/**
 * By Aleksey Timko (de4ltt)*/
fun CreditCard.toDomain() = CreditCardDomain(
    number = number,
    date = date,
    cvc = cvc
)
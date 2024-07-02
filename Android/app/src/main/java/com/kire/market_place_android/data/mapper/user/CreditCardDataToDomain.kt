package com.kire.market_place_android.data.mapper.user

import com.kire.market_place_android.data.model.user.CreditCardEntity
import com.kire.market_place_android.domain.model.user.CreditCardDomain

/**
 * By Michael Gontarev (KiREHwYE)*/
fun CreditCardEntity.toDomain() = CreditCardDomain(
    number = this.number,
    date = this.date,
    cvc = this.cvc
)
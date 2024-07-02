package com.kire.market_place_android.data.mapper.user

import com.kire.market_place_android.data.model.user.UserEntity
import com.kire.market_place_android.domain.model.user.UserDomain

/**
 * By Michael Gontarev (KiREHwYE)*/
fun UserEntity.toDomain() = UserDomain(
    userDiscount = this.userDiscount,
    amountSpent = this.amountSpent,
    username = this.username,
    email = this.email,
    phone = this.phone,
    cardNumber = this.cardNumber,
    CVC = this.CVC,
    validity = this.validity,
    orderCount = this.orderCount,
    redemptionPercent = this.redemptionPercent
)
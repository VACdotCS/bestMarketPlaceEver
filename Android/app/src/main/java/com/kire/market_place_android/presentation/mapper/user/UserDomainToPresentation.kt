package com.kire.market_place_android.presentation.mapper.user

import com.kire.market_place_android.domain.model.user.UserDomain
import com.kire.market_place_android.presentation.model.user.User

/**
 * By Aleksey Timko (de4ltt)*/
fun UserDomain.toPresentation() = User(
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
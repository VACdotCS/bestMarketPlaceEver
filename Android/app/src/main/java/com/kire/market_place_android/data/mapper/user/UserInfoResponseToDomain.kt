package com.kire.market_place_android.data.mapper.user

import com.kire.market_place_android.data.remote.dto.response.user.UserInfoResponse
import com.kire.market_place_android.domain.model.user.UserDomain

fun UserInfoResponse.toDomain() = UserDomain(
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
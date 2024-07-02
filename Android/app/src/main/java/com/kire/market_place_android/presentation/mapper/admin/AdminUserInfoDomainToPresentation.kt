package com.kire.market_place_android.presentation.mapper.admin

import com.kire.market_place_android.domain.model.admin.AdminUserInfoDomain
import com.kire.market_place_android.presentation.mapper.user.toPresentation
import com.kire.market_place_android.presentation.model.admin.AdminUserInfo

fun AdminUserInfoDomain.toPresentation() = AdminUserInfo(
    id = this.id,
    username = this.username,
    role = this.role.toPresentation(),
    userDiscount = this.userDiscount,
    amountSpent = this.amountSpent
)
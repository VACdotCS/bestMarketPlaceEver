package com.kire.market_place_android.data.mapper.admin

import com.kire.market_place_android.data.mapper.user.toDomain
import com.kire.market_place_android.data.remote.dto.response.admin.AdminUserInfoResponse
import com.kire.market_place_android.domain.model.admin.AdminUserInfoDomain

fun AdminUserInfoResponse.toDomain() = AdminUserInfoDomain(
    id = this.id,
    username = this.username,
    role = this.role.toDomain(),
    userDiscount = this.userDiscount,
    amountSpent = this.amountSpent
)
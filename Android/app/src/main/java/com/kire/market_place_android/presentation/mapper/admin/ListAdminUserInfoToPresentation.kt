package com.kire.market_place_android.presentation.mapper.admin

import com.kire.market_place_android.domain.model.admin.AdminUserInfoDomain

fun List<AdminUserInfoDomain>.toPresentation() = this.map { adminUserInfoDomain ->
    adminUserInfoDomain.toPresentation()
}
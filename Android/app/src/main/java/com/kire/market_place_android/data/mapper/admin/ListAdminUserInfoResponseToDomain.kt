package com.kire.market_place_android.data.mapper.admin

import com.kire.market_place_android.data.remote.dto.response.admin.AdminUserInfoResponse

fun List<AdminUserInfoResponse>.toDomain() = this.map { adminUserInfoResponse ->
    adminUserInfoResponse.toDomain()
}
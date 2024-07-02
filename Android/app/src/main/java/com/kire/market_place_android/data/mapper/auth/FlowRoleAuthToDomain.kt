package com.kire.market_place_android.data.mapper.auth

import com.kire.market_place_android.data.mapper.user.toDomain
import com.kire.market_place_android.data.model.auth.Role
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<Role>.toDomain() = map { role ->
    role.toDomain()
}
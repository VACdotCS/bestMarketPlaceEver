package com.kire.market_place_android.presentation.mapper.user

import com.kire.market_place_android.domain.model.user.RoleDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<RoleDomain>.toDomain() = map { role ->
    role.toPresentation()
}
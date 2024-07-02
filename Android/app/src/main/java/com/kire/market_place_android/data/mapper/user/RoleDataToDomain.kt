package com.kire.market_place_android.data.mapper.user

import com.kire.market_place_android.data.model.user.RoleEntity
import com.kire.market_place_android.data.model.auth.Role
import com.kire.market_place_android.domain.model.user.RoleDomain

/**
 * By Michael Gontarev (KiREHwYE)*/
fun RoleEntity.toDomain() =
    RoleDomain.valueOf(this.name)

fun Role.toDomain() =
    RoleDomain.valueOf(this.name)
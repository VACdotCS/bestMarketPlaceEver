package com.kire.market_place_android.presentation.mapper.user

import com.kire.market_place_android.domain.model.user.RoleDomain
import com.kire.market_place_android.presentation.model.user.Role

/**
 * By Aleksey Timko (de4ltt)*/
fun Role.toDomain() = RoleDomain.valueOf(this.name)
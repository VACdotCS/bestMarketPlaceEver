package com.kire.market_place_android.domain.repository

import com.kire.market_place_android.domain.model.auth.AuthResultDomain

/**
 * By Michael Gontarev (KiREHwYE)*/
interface ILogOnRepository {

    suspend fun logOn(
        username: String,
        phone: String,
        email: String,
        password: String
    ) : AuthResultDomain<List<String?>>
}
package com.kire.market_place_android.domain.repository

import com.kire.market_place_android.domain.model.auth.AuthResultDomain

/**
 * By Michael Gontarev (KiREHwYE)*/
interface ILogInRepository {

    suspend fun logIn(phone: String, password: String) : AuthResultDomain<List<String?>>
}
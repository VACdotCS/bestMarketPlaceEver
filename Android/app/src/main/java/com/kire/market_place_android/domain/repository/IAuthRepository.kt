package com.kire.market_place_android.domain.repository

import com.kire.market_place_android.domain.model.auth.AuthResultDomain

import kotlinx.coroutines.flow.Flow

interface IAuthRepository {

    suspend fun logIn(phone: String, password: String): AuthResultDomain<List<String?>>

    suspend fun isTokenExpired(): Boolean

    suspend fun logOn(
        username: String,
        phone: String,
        email: String,
        password: String
    ): AuthResultDomain<List<String?>>

    suspend fun isAuthenticated(): Flow<Boolean>

    suspend fun onLogOut()
}
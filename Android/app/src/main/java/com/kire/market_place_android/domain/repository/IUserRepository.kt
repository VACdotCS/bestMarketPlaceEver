package com.kire.market_place_android.domain.repository

import com.kire.market_place_android.domain.model.IRequestResultDomain
import com.kire.market_place_android.domain.model.user.RoleDomain

import kotlinx.coroutines.flow.Flow

/**
 * By Michael Gontarev (KiREHwYE)*/
interface IUserRepository {

    suspend fun getUserInfo(id: Int) : IRequestResultDomain
    suspend fun getAllUsers(): IRequestResultDomain
    suspend fun getRole(): Flow<RoleDomain>
    suspend fun getUserId(): Flow<Int>
    suspend fun onLogout()
    suspend fun changeUserInfo(
        id: Int,
        username: String,
        phone: String,
        email: String
    ) : IRequestResultDomain
    suspend fun changeUserCard(
        cardNumber: String,
        CVC: String,
        validity: String
    ) : IRequestResultDomain
    suspend fun changePassword(
        currentPassword: String,
        newPassword: String,
        confirmationPassword: String
    ) : IRequestResultDomain
}
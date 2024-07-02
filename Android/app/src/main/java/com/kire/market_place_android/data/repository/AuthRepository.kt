package com.kire.market_place_android.data.repository

import com.kire.market_place_android.data.remote.api.auth.IAuthApi
import com.kire.market_place_android.domain.model.auth.AuthResultDomain

import com.kire.market_place_android.domain.repository.IAuthRepository
import com.kire.market_place_android.domain.repository.ILogInRepository
import com.kire.market_place_android.domain.repository.ILogOnRepository
import com.kire.market_place_android.domain.repository.ITokenStorageRepository

import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class  AuthRepository @Inject constructor(
    private val authApi: IAuthApi,
    private val logInRepository: ILogInRepository,
    private val logOnRepository: ILogOnRepository,
    private val tokenStorageRepository: ITokenStorageRepository
) : IAuthRepository {

    // log-in and return result
    // as sealed class containing authorized, unauthorized, unknown error statuses
    override suspend fun logIn(phone: String, password: String): AuthResultDomain<List<String?>> {
        return logInRepository.logIn(phone = phone, password = password)
    }

    // check if token has already expired and have to be deleted
    override suspend fun isTokenExpired(): Boolean =
        tokenStorageRepository.isTokenExpired()

    // log-on and return result
    // as sealed class containing authorized, unauthorized, unknown error
    // also log-in on success
    override suspend fun logOn(
        username: String,
        phone: String,
        email: String,
        password: String
    ): AuthResultDomain<List<String?>> {
        return logOnRepository.logOn(
            username = username,
            phone = phone,
            email = email,
            password = password
        ).also {
            logInRepository.logIn(
                phone = phone,
                password = password
            )
        }
    }

    // check if token is still saved in the storage
    override suspend fun isAuthenticated(): Flow<Boolean> {
        return tokenStorageRepository.isAuthenticated()
    }

    // delete token from local storage
    override suspend fun onLogOut() {
        tokenStorageRepository.onLogout()
        authApi.triggerLoadTokens()
    }


}
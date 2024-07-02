package com.kire.market_place_android.data.repository

import com.kire.audio.data.preferencesDataStore.ITokenStorage
import com.kire.market_place_android.data.mapper.auth.toDomain

import com.kire.market_place_android.data.model.auth.Token
import com.kire.market_place_android.di.IoDispatcher
import com.kire.market_place_android.domain.model.user.RoleDomain
import com.kire.market_place_android.domain.repository.ITokenStorageRepository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

import javax.inject.Inject

class TokenStorageRepository @Inject constructor(
    private val tokenStorage: ITokenStorage,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : ITokenStorageRepository {

    // check if token is still saved in local storage
    override fun isAuthenticated(): Flow<Boolean> =
        tokenStorage.isAuthenticated()

    // check if token is already expired and needs to be deleted
    override suspend fun isTokenExpired(): Boolean =
        withContext(coroutineDispatcher) {
            tokenStorage.isTokenExpired()
        }

    // store token in local storage
    override suspend fun store(token: Token) =
        withContext(coroutineDispatcher) {
            tokenStorage.store(token)
        }

    // get saved token
    override suspend fun getToken(): Token =
        withContext(coroutineDispatcher) {
            tokenStorage.getToken()
        }

    // get user's role
    override suspend fun getRole(): Flow<RoleDomain> =
        withContext(coroutineDispatcher) {
            tokenStorage.getRole().toDomain()
        }

    // get user's id
    override suspend fun getUserId(): Flow<Int> =
        withContext(coroutineDispatcher) {
            tokenStorage.getUserId()
        }

    // log-out user by deleting his token from local storage
    override suspend fun onLogout() =
        withContext(coroutineDispatcher) {
            tokenStorage.onLogout()
        }

}

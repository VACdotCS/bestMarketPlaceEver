package com.kire.market_place_android.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

import com.kire.audio.data.preferencesDataStore.ITokenStorage

import com.kire.market_place_android.data.model.auth.Token
import com.kire.market_place_android.data.local.datastore.util.TokenStorageConstants
import com.kire.market_place_android.data.model.auth.Role

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

import javax.inject.Inject

class TokenStorage @Inject constructor(
    private val dataStore: DataStore<Preferences>
): ITokenStorage {

    // returns a flow of is authenticated state
    override fun isAuthenticated(): Flow<Boolean> {
        // flow of token existence from dataStore
        return dataStore.data.map {
            it[TokenStorageConstants.TOKEN_KEY]?.isNotEmpty() == true
        }
    }

    // check token expiration status
    override suspend fun isTokenExpired(): Boolean {
        val preferences = dataStore.data.first()
        val timestamp = preferences[TokenStorageConstants.TOKEN_TIMESTAMP] ?: return true
        val currentTime = System.currentTimeMillis()
        //Check if 24 hours passed since last token refresh (-60 * 1000 just in case)
        return (currentTime - timestamp) >= 24 * 60 * 60 * 1000 - 60 * 1000
    }

    // store new token after sign in or token refresh
    override suspend fun store(token: Token) {
        val currentTime = System.currentTimeMillis()
        // store token to dataStore
        dataStore.edit {
            it[TokenStorageConstants.TOKEN_KEY] = token.token
            it[TokenStorageConstants.TOKEN_TIMESTAMP] = currentTime
            it[TokenStorageConstants.ROLE_KEY] = token.role.toString()
            it[TokenStorageConstants.USER_ID_KEY] = token.userId
        }
    }

    // get token for protected API method
    override suspend fun getToken(): Token {
        return dataStore.data
            .map {
                Token(
                    token = it[TokenStorageConstants.TOKEN_KEY] ?: "",
                    role = Role.valueOf(it[TokenStorageConstants.ROLE_KEY] ?: "USER"),
                    userId = it[TokenStorageConstants.USER_ID_KEY] ?: 0
                )
            }
            // get a flow of token from dataStore
            .firstOrNull() // transform flow to suspend
            ?: throw IllegalArgumentException("no token stored")
    }

    // get user's role from TokenResponse
    override suspend fun getRole(): Flow<Role> {
        return dataStore.data.map {
            Role.valueOf(it[TokenStorageConstants.ROLE_KEY] ?: "USER")
        }
    }

    // get user's id from TokenResponse
    override suspend fun getUserId(): Flow<Int> {
        return dataStore.data.map {
            it[TokenStorageConstants.USER_ID_KEY] ?: 0
        }
    }

    // delete saved token
    override suspend fun onLogout() {
        // remove token from dataStore
        dataStore.edit {
            it.remove(TokenStorageConstants.TOKEN_KEY)
            it.remove(TokenStorageConstants.TOKEN_TIMESTAMP)
            it.remove(TokenStorageConstants.ROLE_KEY)
            it.remove(TokenStorageConstants.USER_ID_KEY)
        }
    }
}
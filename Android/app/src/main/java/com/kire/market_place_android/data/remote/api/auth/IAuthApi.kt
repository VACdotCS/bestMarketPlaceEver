package com.kire.market_place_android.data.remote.api.auth

import com.kire.market_place_android.data.remote.dto.request.auth.LogInRequest
import com.kire.market_place_android.data.remote.dto.request.auth.LogOnRequest
import com.kire.market_place_android.data.remote.dto.response.auth.TokenResponse

interface IAuthApi {

    suspend fun logOn(request: LogOnRequest) : TokenResponse
    suspend fun logIn(request: LogInRequest) : TokenResponse
    suspend fun triggerLoadTokens()
}
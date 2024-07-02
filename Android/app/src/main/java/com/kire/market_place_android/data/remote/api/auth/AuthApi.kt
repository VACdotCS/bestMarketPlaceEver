package com.kire.market_place_android.data.remote.api.auth

import com.kire.market_place_android.data.remote.dto.HttpRoutes
import com.kire.market_place_android.data.remote.dto.request.auth.LogInRequest
import com.kire.market_place_android.data.remote.dto.request.auth.LogOnRequest
import com.kire.market_place_android.data.remote.dto.response.auth.TokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerAuthProvider
import io.ktor.client.plugins.plugin
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class AuthApi @Inject constructor(
    private val client: HttpClient
) : IAuthApi {

    // log-on and get access token from server
    override suspend fun logOn(request: LogOnRequest) : TokenResponse {
        return client.post {
            url(HttpRoutes.AUTH_REGISTER)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<TokenResponse>()
    }

    // log-in and get access token from server
    override suspend fun logIn(request: LogInRequest) : TokenResponse {
        return client.post {
            url(HttpRoutes.AUTH_LOGIN)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<TokenResponse>()
    }

    // loadTokens block is called once
    // so you need to trigger loadTokens block from ktor client
    // after token was received
    override suspend fun triggerLoadTokens() {
        client.plugin(Auth).providers
            .filterIsInstance<BearerAuthProvider>()
            .first().clearToken()
    }
}
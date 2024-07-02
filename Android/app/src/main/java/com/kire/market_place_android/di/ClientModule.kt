package com.kire.market_place_android.di

import android.util.Log
import com.kire.market_place_android.data.remote.dto.Errors
import com.kire.market_place_android.domain.repository.ITokenStorageRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClientModule {

    @Provides
    @Singleton
    fun provideApiClient(
        tokenStorageRepository: ITokenStorageRepository
    ): HttpClient {

        return HttpClient(OkHttp) {
            expectSuccess = true

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("HTTP call", message)
                    }
                }
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("MINE","HTTP status: ${response.status.value}")
                }
            }

            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, _ ->
                    val response = when(exception) {
                        is ClientRequestException -> {
                            val exceptionResponse = exception.response
                            try {
                                exceptionResponse.body<Errors>()
                            } catch (e: JsonConvertException) {
                                val body = exceptionResponse.body<String>()
                                Errors(errors = listOf(body))
                            }
                        }
                        is ServerResponseException -> {
                            val exceptionResponse = exception.response
                            try {
                                exceptionResponse.body<Errors>()
                            } catch (e: JsonConvertException) {
                                val body = exceptionResponse.body<String>()
                                Errors(errors = listOf(body))
                            }
                        }

                        else -> {
                            Errors()
                        }
                    }

                    if (response.errors.isNotEmpty())
                        throw response
                }
            }

            install(Auth) {
                bearer {

                    loadTokens {
                        val accessToken = tokenStorageRepository.getToken().token

                        BearerTokens(accessToken = accessToken, refreshToken = accessToken)
                    }

                    // it's possible to save user's phone and password and trigger re-login here
                    // it'd be an illusion of refreshing

//                    refreshTokens {
//                        val accessToken = tokenStorageRepository.getToken().accessToken
//                        val refreshToken = tokenStorageRepository.getToken().refreshToken
//
//                        val refreshTokenInfo: Token = client.submitForm(
//                            url = "https://accounts.google.com/o/oauth2/token",
//                            formParameters = parameters {
//                                append("refreshToken", refreshToken)
//                            }
//                        ) { markAsRefreshTokenRequest() }.body()
//
//                        tokenStorageRepository.store(
//                            Token(accessToken = accessToken, refreshToken = refreshTokenInfo.refreshToken)
//                        )
//
//                        val newToken = tokenStorageRepository.getToken()
//
//                        BearerTokens(
//                            accessToken = newToken.accessToken,
//                            refreshToken = newToken.refreshToken
//                        )
//                    }
                }
            }
        }
    }
}
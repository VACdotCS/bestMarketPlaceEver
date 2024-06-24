package com.kire.market_place_android.data.repository

import com.kire.market_place_android.domain.model.auth.AuthResultDomain
import com.kire.market_place_android.data.model.auth.Token
import com.kire.market_place_android.data.remote.dto.request.auth.LogInRequest
import com.kire.market_place_android.data.remote.dto.Error
import com.kire.market_place_android.data.remote.api.auth.IAuthApi
import com.kire.market_place_android.di.IoDispatcher
import com.kire.market_place_android.domain.repository.ILogInRepository
import com.kire.market_place_android.domain.repository.ITokenStorageRepository

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

import javax.inject.Inject

/**
 * By Michael Gontarev (KiREHwYE)*/
class LogInRepository @Inject constructor(
    private val authApi: IAuthApi,
    private val tokenStorageRepository: ITokenStorageRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
): ILogInRepository {

    // log-in and return result
    // as sealed class containing authorized, unauthorized, unknown error statuses
    override suspend fun logIn(phone: String, password: String) : AuthResultDomain<String> {

        return withContext(coroutineDispatcher) {

            try {
                val response = authApi.logIn(
                    request = LogInRequest(
                        phone = phone,
                        password = password
                    )
                )

                tokenStorageRepository.store(
                    token = Token(
                        token = response.token,
                        role = response.role,
                        userId = response.userId
                    )
                )

                authApi.triggerLoadTokens()

                AuthResultDomain.Authorized()

            } catch (e: Error){
                AuthResultDomain.UnknownError(e.message)

            } catch (e: RedirectResponseException) {
                AuthResultDomain.UnknownError(e.response.bodyAsText())

            } catch (e: ClientRequestException) {

                AuthResultDomain.UnknownError(e.response.bodyAsText())

            } catch (e: ServerResponseException) {
                AuthResultDomain.UnknownError(e.response.bodyAsText())

            } catch (e: JsonConvertException) {
                AuthResultDomain.UnknownError(e.message)

            } catch (e: NoTransformationFoundException) {
                AuthResultDomain.UnknownError(e.message)

            } catch (e: Exception) {
                AuthResultDomain.UnknownError(e.message)
            }
        }
    }
}
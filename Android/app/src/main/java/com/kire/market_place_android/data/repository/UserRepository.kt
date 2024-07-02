package com.kire.market_place_android.data.repository

import com.kire.market_place_android.data.mapper.admin.toDomain
import com.kire.market_place_android.data.mapper.user.toDomain
import com.kire.market_place_android.data.remote.dto.request.user.ChangePasswordRequest
import com.kire.market_place_android.data.remote.dto.request.user.ChangeUserCardRequest
import com.kire.market_place_android.data.remote.dto.request.user.ChangeUserInfoRequest
import com.kire.market_place_android.data.remote.api.user.IUserApi
import com.kire.market_place_android.data.remote.dto.Errors
import com.kire.market_place_android.di.IoDispatcher
import com.kire.market_place_android.domain.model.IRequestResultDomain
import com.kire.market_place_android.domain.model.user.RoleDomain
import com.kire.market_place_android.domain.repository.ITokenStorageRepository
import com.kire.market_place_android.domain.repository.IUserRepository

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.JsonConvertException

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

import javax.inject.Inject

/**
 * By Michael Gontarev (KiREHwYE)*/
class UserRepository @Inject constructor(
    private val userApi: IUserApi,
    private val tokenStorageRepository: ITokenStorageRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
): IUserRepository {

    // get user's info by his id
    // return Success with resulting list or Error status from IUserResultDomain sealed interface
    override suspend fun getUserInfo(id: Int): IRequestResultDomain {
        return withContext(coroutineDispatcher) {
            try {
                val response = userApi.getUserInfoById(id).toDomain()
                IRequestResultDomain.Success(response)
            } catch (e: Errors){
                IRequestResultDomain.Errors(e.errors)

            } catch (e: RedirectResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ClientRequestException) {

                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ServerResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: JsonConvertException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: NoTransformationFoundException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: Exception) {
                IRequestResultDomain.Errors(listOf(e.message))
            }
        }
    }

    // get all users that use KubMarket
    // return Success with resulting list or Error status from IAdminResultDomain sealed interface
    override suspend fun getAllUsers(): IRequestResultDomain {
        return withContext(coroutineDispatcher) {
            try {
                val response = userApi.getAllUsers().toDomain()
                IRequestResultDomain.Success(response)
            } catch (e: Errors){
                IRequestResultDomain.Errors(e.errors)

            } catch (e: RedirectResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ClientRequestException) {

                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ServerResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: JsonConvertException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: NoTransformationFoundException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: Exception) {
                IRequestResultDomain.Errors(listOf(e.message))
            }
        }
    }

    // get user's role
    override suspend fun getRole(): Flow<RoleDomain> =
        tokenStorageRepository.getRole()

    // get user's id
    override suspend fun getUserId(): Flow<Int> =
        tokenStorageRepository.getUserId()

    // log-out user by deleting his token from local storage
    override suspend fun onLogout() =
        tokenStorageRepository.onLogout()

    // change user's info by his id
    // return SuccessfullyChanged or Error status from IUserResultDomain sealed interface
    override suspend fun changeUserInfo(
        id: Int,
        username: String,
        phone: String,
        email: String
    ): IRequestResultDomain {

        return  withContext(coroutineDispatcher) {
            try {
                userApi.changeUserInfo(
                    id = id,
                    request = ChangeUserInfoRequest(
                        username = username,
                        phone = phone,
                        email = email
                    )
                )
                IRequestResultDomain.SuccessfullyDone
            } catch (e: Errors){
                IRequestResultDomain.Errors(e.errors)

            } catch (e: RedirectResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ClientRequestException) {

                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ServerResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: JsonConvertException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: NoTransformationFoundException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: Exception) {
                IRequestResultDomain.Errors(listOf(e.message))
            }
        }
    }

    // change user's payment card by his id
    // return SuccessfullyChanged or Error status from IUserResultDomain sealed interface
    override suspend fun changeUserCard(
        cardNumber: String,
        CVC: String,
        validity: String
    ) : IRequestResultDomain {

        return withContext(coroutineDispatcher) {
            try {
                userApi.changeUserCard(
                    request = ChangeUserCardRequest(
                        cardNumber = cardNumber,
                        CVC = CVC,
                        validity = validity
                    )
                )
                IRequestResultDomain.SuccessfullyDone

            } catch (e: Errors){
                IRequestResultDomain.Errors(e.errors)

            } catch (e: RedirectResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ClientRequestException) {

                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ServerResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: JsonConvertException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: NoTransformationFoundException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: Exception) {
                IRequestResultDomain.Errors(listOf(e.message))
            }
        }
    }

    // change user's password by his id
    // return SuccessfullyChanged or Error status from IUserResultDomain sealed interface
    override suspend fun changePassword(
        currentPassword: String,
        newPassword: String,
        confirmationPassword: String
    ) : IRequestResultDomain {
        return withContext(coroutineDispatcher) {
            try {
                userApi.changePassword(
                    request = ChangePasswordRequest(
                        currentPassword = currentPassword,
                        newPassword = newPassword,
                        confirmationPassword = confirmationPassword
                    )
                )
                IRequestResultDomain.SuccessfullyDone

            } catch (e: Errors){
                IRequestResultDomain.Errors(e.errors)

            } catch (e: RedirectResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ClientRequestException) {

                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ServerResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: JsonConvertException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: NoTransformationFoundException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: Exception) {
                IRequestResultDomain.Errors(listOf(e.message))
            }
        }
    }
}
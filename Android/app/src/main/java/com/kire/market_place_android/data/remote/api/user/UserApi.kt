package com.kire.market_place_android.data.remote.api.user

import com.kire.market_place_android.data.remote.dto.HttpRoutes
import com.kire.market_place_android.data.remote.dto.request.user.ChangePasswordRequest
import com.kire.market_place_android.data.remote.dto.request.user.ChangeUserCardRequest
import com.kire.market_place_android.data.remote.dto.request.user.ChangeUserInfoRequest
import com.kire.market_place_android.data.remote.dto.response.admin.AdminUserInfoResponse
import com.kire.market_place_android.data.remote.dto.response.user.UserInfoResponse

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

import javax.inject.Inject

class UserApi @Inject constructor(
    private val client: HttpClient
) : IUserApi {

    // get info about user by his id
    override suspend fun getUserInfoById(id: Int): UserInfoResponse {
        return client.get {
            url(HttpRoutes.USER_INFO_BY_ID + id.toString())
            contentType(ContentType.Application.Json)
        }.body<UserInfoResponse>()
    }

    // get all users that use KubMarket
    override suspend fun getAllUsers(): List<AdminUserInfoResponse> {
        return client.get {
            url(HttpRoutes.ALL_USERS)
            contentType(ContentType.Application.Json)
        }.body<List<AdminUserInfoResponse>>()
    }

    // change user's password
    override suspend fun changePassword(request: ChangePasswordRequest) {
        client.patch {
            url(HttpRoutes.CHANGE_PASSWORD)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    // change user's payment card
    override suspend fun changeUserCard(request: ChangeUserCardRequest) {
        client.patch {
            url(HttpRoutes.CHANGE_CARD)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    // change user's info
    override suspend fun changeUserInfo(id: Int, request: ChangeUserInfoRequest) {
        client.patch {
            url(HttpRoutes.CHANGE_INFO_BY_ID + id.toString())
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }
}
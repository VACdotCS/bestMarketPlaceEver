package com.kire.market_place_android.data.remote.api.user

import com.kire.market_place_android.data.remote.dto.request.user.ChangePasswordRequest
import com.kire.market_place_android.data.remote.dto.request.user.ChangeUserCardRequest
import com.kire.market_place_android.data.remote.dto.request.user.ChangeUserInfoRequest
import com.kire.market_place_android.data.remote.dto.response.admin.AdminUserInfoResponse
import com.kire.market_place_android.data.remote.dto.response.user.UserInfoResponse

interface IUserApi {

    suspend fun getUserInfoById(id: Int): UserInfoResponse
    suspend fun getAllUsers(): List<AdminUserInfoResponse>
    suspend fun changePassword(request: ChangePasswordRequest)
    suspend fun changeUserCard(request: ChangeUserCardRequest)
    suspend fun changeUserInfo(id: Int, request: ChangeUserInfoRequest)
}
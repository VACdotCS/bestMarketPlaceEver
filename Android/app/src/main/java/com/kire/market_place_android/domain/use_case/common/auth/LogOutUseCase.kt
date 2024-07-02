package com.kire.market_place_android.domain.use_case.common.auth

import com.kire.market_place_android.domain.repository.IUserRepository

import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {

    suspend operator fun invoke() =
        userRepository.onLogout()
}
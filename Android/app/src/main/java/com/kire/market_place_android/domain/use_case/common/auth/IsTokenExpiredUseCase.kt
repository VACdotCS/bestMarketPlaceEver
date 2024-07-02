package com.kire.market_place_android.domain.use_case.common.auth

import com.kire.market_place_android.domain.repository.IAuthRepository
import javax.inject.Inject

class IsTokenExpiredUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke() : Boolean {
        return authRepository.isTokenExpired()
    }
}
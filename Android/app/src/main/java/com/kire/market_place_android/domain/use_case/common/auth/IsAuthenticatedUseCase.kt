package com.kire.market_place_android.domain.use_case.common.auth

import com.kire.market_place_android.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsAuthenticatedUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {

    suspend operator fun invoke() : Flow<Boolean> =
        authRepository.isAuthenticated()
}
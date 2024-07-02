package com.kire.market_place_android.domain.use_case.common.auth

import com.kire.market_place_android.domain.model.auth.AuthResultDomain
import com.kire.market_place_android.domain.repository.IAuthRepository

import javax.inject.Inject

/**
 * By Michael Gontarev (KiREHwYE)*/
class LogInUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(phone: String, password: String) =
        authRepository.logIn(phone = phone, password = password)
}
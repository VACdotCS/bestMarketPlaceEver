package com.kire.market_place_android.domain.use_case.common.util

import com.kire.market_place_android.domain.use_case.common.auth.IsAuthenticatedUseCase
import com.kire.market_place_android.domain.use_case.common.auth.IsTokenExpiredUseCase
import com.kire.market_place_android.domain.use_case.common.auth.LogInUseCase
import com.kire.market_place_android.domain.use_case.common.auth.LogOnUseCase

sealed interface IAuthUseCases {
    val logInUseCase: LogInUseCase
    val logOnUseCase: LogOnUseCase
    val isAuthenticatedUseCase: IsAuthenticatedUseCase
    val isTokenExpiredUseCase: IsTokenExpiredUseCase
}
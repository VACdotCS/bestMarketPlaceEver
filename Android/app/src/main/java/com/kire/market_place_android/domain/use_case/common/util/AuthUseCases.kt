package com.kire.market_place_android.domain.use_case.common.util

import com.kire.market_place_android.domain.use_case.common.auth.IsAuthenticatedUseCase
import com.kire.market_place_android.domain.use_case.common.auth.IsTokenExpiredUseCase
import com.kire.market_place_android.domain.use_case.common.auth.LogInUseCase
import com.kire.market_place_android.domain.use_case.common.auth.LogOnUseCase
import javax.inject.Inject

data class AuthUseCases @Inject constructor(
    override val logInUseCase: LogInUseCase,
    override val logOnUseCase: LogOnUseCase,
    override val isAuthenticatedUseCase: IsAuthenticatedUseCase,
    override val isTokenExpiredUseCase: IsTokenExpiredUseCase
) : IAuthUseCases
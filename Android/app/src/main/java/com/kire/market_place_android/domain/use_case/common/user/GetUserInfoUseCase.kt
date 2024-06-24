package com.kire.market_place_android.domain.use_case.common.user

import com.kire.market_place_android.domain.model.user.IUserResultDomain
import com.kire.market_place_android.domain.repository.IUserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {

    suspend operator fun invoke(id: Int): IUserResultDomain =
        userRepository.getUserInfo(id)
}
package com.kire.market_place_android.domain.use_case.common.user

import com.kire.market_place_android.domain.model.user.RoleDomain
import com.kire.market_place_android.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoleUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {

    suspend operator fun invoke(): Flow<RoleDomain> =
        userRepository.getRole()
}
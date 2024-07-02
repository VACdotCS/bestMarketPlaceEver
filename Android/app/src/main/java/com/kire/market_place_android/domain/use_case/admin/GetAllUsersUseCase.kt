package com.kire.market_place_android.domain.use_case.admin

import com.kire.market_place_android.domain.repository.IUserRepository
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {

    suspend operator fun invoke() =
        userRepository.getAllUsers()
}
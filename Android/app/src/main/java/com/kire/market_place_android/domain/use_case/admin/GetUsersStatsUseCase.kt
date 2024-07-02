package com.kire.market_place_android.domain.use_case.admin

import com.kire.market_place_android.domain.repository.IUserRepository
import javax.inject.Inject

/**
 * By Aleksey Timko (de4ltt)*/
class GetUsersStatsUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    operator fun invoke() {
        /*TODO*/
    }
}
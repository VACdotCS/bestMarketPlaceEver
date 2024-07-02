package com.kire.market_place_android.domain.use_case.common.user

import com.kire.market_place_android.domain.model.IRequestResultDomain
import com.kire.market_place_android.domain.repository.IUserRepository
import javax.inject.Inject

class ChangeUserCardUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {

    suspend operator fun invoke(
        cardNumber: String,
        CVC: String,
        validity: String
    ) : IRequestResultDomain {
        return userRepository.changeUserCard(
            cardNumber = cardNumber,
            CVC = CVC,
            validity = validity
        )
    }
}
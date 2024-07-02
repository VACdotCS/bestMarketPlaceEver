package com.kire.market_place_android.domain.use_case.admin

import com.kire.market_place_android.domain.repository.IPickUpPointRepository
import javax.inject.Inject

class CreatePickUpPointUseCase @Inject constructor(
    private val pickUpPointRepository: IPickUpPointRepository
) {
    suspend operator fun invoke(managerId: Int, address: String) =
        pickUpPointRepository.createPickUpPoint(managerId = managerId, address = address)
}
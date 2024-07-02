package com.kire.market_place_android.domain.use_case.admin

import com.kire.market_place_android.domain.repository.IPickUpPointRepository
import javax.inject.Inject

/**
 * By Aleksey Timko (de4ltt)*/
class UpdatePickUpPointUseCase @Inject constructor(
    private val pickUpPointRepository: IPickUpPointRepository
) {
    suspend operator fun invoke(
        id: Int,
        managerId: Int,
        address: String
    ) = pickUpPointRepository.updatePickUpPoint(id, managerId, address)
}
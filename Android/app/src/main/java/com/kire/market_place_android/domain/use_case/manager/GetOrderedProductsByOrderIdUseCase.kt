package com.kire.market_place_android.domain.use_case.manager

import com.kire.market_place_android.domain.repository.IOrderRepository
import javax.inject.Inject

class GetOrderedProductsByOrderIdUseCase @Inject constructor(
    private val orderRepository: IOrderRepository
) {
    suspend operator fun invoke(id: Int) =
        orderRepository.getOrderedProductsByOrderId(id = id)
}
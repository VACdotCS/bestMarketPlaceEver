package com.kire.market_place_android.domain.use_case.common

import com.kire.market_place_android.domain.repository.IOrderRepository
import javax.inject.Inject

class OrderUseCase @Inject constructor(
    private val orderRepository: IOrderRepository
) {

    operator fun invoke() = { /*TODO*/ }
}
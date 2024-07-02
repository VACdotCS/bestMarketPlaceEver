package com.kire.market_place_android.domain.use_case.common.order

import com.kire.market_place_android.domain.model.order.OrderedProductDomain
import com.kire.market_place_android.domain.repository.IOrderRepository

import java.math.BigDecimal
import javax.inject.Inject

class CreateOrderUseCase @Inject constructor(
    private val orderRepository: IOrderRepository
) {

    suspend operator fun invoke(pickUpPointId: Int, orderedProducts: List<OrderedProductDomain>, orderPrice: BigDecimal) =
        orderRepository.createOrder(
            pickUpPointId = pickUpPointId,
            orderedProducts = orderedProducts,
            orderPrice = orderPrice
        )
}
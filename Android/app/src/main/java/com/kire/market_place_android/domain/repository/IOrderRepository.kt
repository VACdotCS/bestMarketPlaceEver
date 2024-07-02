package com.kire.market_place_android.domain.repository

import com.kire.market_place_android.domain.model.IRequestResultDomain
import com.kire.market_place_android.domain.model.order.OrderedProductDomain

import java.math.BigDecimal

/**
 * @author Michael Gontarev (KiREHwYE)*/
interface IOrderRepository {

    suspend fun getOrdersByUser(): IRequestResultDomain
    suspend fun getOrderedProductsByOrderId(id: Int): IRequestResultDomain
    suspend fun createOrder(pickUpPointId: Int, orderedProducts: List<OrderedProductDomain>, orderPrice: BigDecimal): IRequestResultDomain
    suspend fun confirmOrder(id: Int, received: List<Int>, returned: List<Int>): IRequestResultDomain
}
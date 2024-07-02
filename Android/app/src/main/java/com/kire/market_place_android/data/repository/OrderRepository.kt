package com.kire.market_place_android.data.repository

import android.util.Log
import com.kire.market_place_android.data.mapper.order.toDomain
import com.kire.market_place_android.data.mapper.order.toResponse
import com.kire.market_place_android.data.mapper.product.toRequest
import com.kire.market_place_android.data.remote.api.manager.IManagerApi
import com.kire.market_place_android.data.remote.api.order.IOrderApi
import com.kire.market_place_android.data.remote.dto.Errors
import com.kire.market_place_android.data.remote.dto.request.order.ConfirmOrderRequest
import com.kire.market_place_android.data.remote.dto.request.order.OrderRequest
import com.kire.market_place_android.di.IoDispatcher
import com.kire.market_place_android.domain.model.IRequestResultDomain
import com.kire.market_place_android.domain.model.order.OrderedProductDomain
import com.kire.market_place_android.domain.repository.IOrderRepository

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.JsonConvertException

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

import java.math.BigDecimal
import javax.inject.Inject

/**
 * By Michael Gontarev (KiREHwYE)*/
class OrderRepository @Inject constructor(
    private val orderApi: IOrderApi,
    private val managerApi: IManagerApi,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
): IOrderRepository {

    // get all user's orders and return result
    // as sealed interface containing success, idle, error statuses
    override suspend fun getOrdersByUser(): IRequestResultDomain {
        return withContext(coroutineDispatcher) {
            try {
                val response = orderApi.getOrdersByUser()

                IRequestResultDomain.Success(response.toDomain())
            } catch (e: Errors){
                IRequestResultDomain.Errors(e.errors)

            } catch (e: RedirectResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ClientRequestException) {

                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ServerResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: JsonConvertException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: NoTransformationFoundException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: Exception) {
                IRequestResultDomain.Errors(listOf(e.message))
            }
        }
    }

    override suspend fun getOrderedProductsByOrderId(id: Int): IRequestResultDomain {
        return withContext(coroutineDispatcher) {
            try {
                val response = managerApi.getOrderedProductsByOrderId(id)

                IRequestResultDomain.Success(response.toDomain())
            } catch (e: Errors){
                IRequestResultDomain.Errors(e.errors)

            } catch (e: RedirectResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ClientRequestException) {

                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ServerResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: JsonConvertException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: NoTransformationFoundException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: Exception) {
                IRequestResultDomain.Errors(listOf(e.message))
            }
        }
    }

    override suspend fun createOrder(
        pickUpPointId: Int,
        orderedProducts: List<OrderedProductDomain>,
        orderPrice: BigDecimal
    ): IRequestResultDomain {
        return withContext(coroutineDispatcher) {
            try {
                orderApi.createOrder(
                    orderRequest =
                        OrderRequest(
                            pickUpPointId = pickUpPointId,
                            orderedProducts = orderedProducts.toRequest(),
                            orderPrice = orderPrice
                        )
                )

                IRequestResultDomain.SuccessfullyDone
            } catch (e: Errors){
                IRequestResultDomain.Errors(e.errors)

            } catch (e: RedirectResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ClientRequestException) {

                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ServerResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: JsonConvertException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: NoTransformationFoundException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: Exception) {
                IRequestResultDomain.Errors(listOf(e.message))
            }
        }
    }

    override suspend fun confirmOrder(
        id: Int,
        received: List<Int>,
        returned: List<Int>
    ): IRequestResultDomain {
        return withContext(coroutineDispatcher) {
            try {
                managerApi.confirmOrder(
                    id = id,
                    confirmOrderRequest =
                        ConfirmOrderRequest(
                            received = received,
                            returned = returned
                        )
                )

                IRequestResultDomain.SuccessfullyDone
            } catch (e: Errors){
                IRequestResultDomain.Errors(e.errors)

            } catch (e: RedirectResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ClientRequestException) {

                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: ServerResponseException) {
                IRequestResultDomain.Errors(listOf( e.response.bodyAsText()))

            } catch (e: JsonConvertException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: NoTransformationFoundException) {
                IRequestResultDomain.Errors(listOf(e.message))

            } catch (e: Exception) {
                IRequestResultDomain.Errors(listOf(e.message))
            }
        }
    }
}
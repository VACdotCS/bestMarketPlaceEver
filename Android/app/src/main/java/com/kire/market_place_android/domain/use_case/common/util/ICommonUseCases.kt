package com.kire.market_place_android.domain.use_case.common.util

import com.kire.market_place_android.domain.use_case.admin.GetAllPickUpPointsUseCase
import com.kire.market_place_android.domain.use_case.common.user.GetRoleUseCase
import com.kire.market_place_android.domain.use_case.common.user.GetUserIdUseCase
import com.kire.market_place_android.domain.use_case.common.user.GetUserInfoUseCase
import com.kire.market_place_android.domain.use_case.common.auth.IsAuthenticatedUseCase
import com.kire.market_place_android.domain.use_case.common.auth.LogOutUseCase
import com.kire.market_place_android.domain.use_case.common.order.CreateOrderUseCase
import com.kire.market_place_android.domain.use_case.common.order.GetOrdersUseCase
import com.kire.market_place_android.domain.use_case.common.product.GetAllAvailableCategoriesUseCase
import com.kire.market_place_android.domain.use_case.common.product.ProductAddToCartUseCase
import com.kire.market_place_android.domain.use_case.common.product.ProductDeleteFromCartUseCase
import com.kire.market_place_android.domain.use_case.common.product.GetAllProductsUseCase
import com.kire.market_place_android.domain.use_case.common.user.ChangePasswordUseCase
import com.kire.market_place_android.domain.use_case.common.user.ChangeUserCardUseCase
import com.kire.market_place_android.domain.use_case.common.user.ChangeUserInfoAndReturnUseCase

/**
 * @author Michael Gontarev (KiREHwYE)
 * @author Aleksey Timko (de4ltt)*/
sealed interface ICommonUseCases {
    val logOutUseCase: LogOutUseCase
    val getOrdersUseCase: GetOrdersUseCase
    val productAddToCartUseCase: ProductAddToCartUseCase
    val productDeleteFromCartUseCase: ProductDeleteFromCartUseCase
    val getAllProductsUseCase: GetAllProductsUseCase
    val getUserIdUseCase: GetUserIdUseCase
    val getRoleUseCase: GetRoleUseCase
    val getUserInfoUseCase: GetUserInfoUseCase
    val isAuthenticatedUseCase: IsAuthenticatedUseCase
    val changePasswordUseCase: ChangePasswordUseCase
    val changeUserCardUseCase: ChangeUserCardUseCase
    val changeUserInfoAndReturnUseCase: ChangeUserInfoAndReturnUseCase
    val getAllAvailableCategoriesUseCase: GetAllAvailableCategoriesUseCase
    val createOrderUseCase: CreateOrderUseCase
    val getAllPickUpPoints: GetAllPickUpPointsUseCase
}
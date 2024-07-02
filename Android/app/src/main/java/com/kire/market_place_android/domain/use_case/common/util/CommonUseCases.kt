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
import javax.inject.Inject

/**
 * @author Michael Gontarev (KiREHwYE)
 * @author Aleksey Timko (de4ltt)*/
data class CommonUseCases @Inject constructor(
    override val getOrdersUseCase: GetOrdersUseCase,
    override val productAddToCartUseCase: ProductAddToCartUseCase,
    override val productDeleteFromCartUseCase: ProductDeleteFromCartUseCase,
    override val logOutUseCase: LogOutUseCase,
    override val getUserIdUseCase: GetUserIdUseCase,
    override val getRoleUseCase: GetRoleUseCase,
    override val getUserInfoUseCase: GetUserInfoUseCase,
    override val isAuthenticatedUseCase: IsAuthenticatedUseCase,
    override val changePasswordUseCase: ChangePasswordUseCase,
    override val changeUserCardUseCase: ChangeUserCardUseCase,
    override val changeUserInfoAndReturnUseCase: ChangeUserInfoAndReturnUseCase,
    override val getAllProductsUseCase: GetAllProductsUseCase,
    override val getAllAvailableCategoriesUseCase: GetAllAvailableCategoriesUseCase,
    override val createOrderUseCase: CreateOrderUseCase,
    override val getAllPickUpPoints: GetAllPickUpPointsUseCase
) : ICommonUseCases

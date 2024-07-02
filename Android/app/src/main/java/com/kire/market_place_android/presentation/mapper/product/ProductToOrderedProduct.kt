package com.kire.market_place_android.presentation.mapper.product

import com.kire.market_place_android.presentation.model.order.OrderedProduct
import com.kire.market_place_android.presentation.model.product.Product
import java.time.LocalDate

fun Product.toOrderedProduct() = OrderedProduct(
    product = this.toCompactProduct(),
    quantity = this.chosenQuantity,
    price = this.price,
    deliveryStatus = "оформлен",
    deliveryDays = this.deliveryDays,
    completionDate = LocalDate.now().plusDays(this.deliveryDays.toLong())
)
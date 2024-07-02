package com.kire.market_place_android.data.model.product

import android.net.Uri

/**
 * By Aleksey Timko (de4ltt)*/
data class ProductItemEntity(
    val itemName: String,
    val itemPrice: String,
    val itemScale: String,
    val itemDiscountPrice: String,
    val imageUri: Uri?,
    val isFavourite: Boolean,
    val itemDescription: String
)
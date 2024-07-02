package com.kire.market_place_android.data.remote.api.product

import com.kire.market_place_android.data.remote.dto.HttpRoutes
import com.kire.market_place_android.data.remote.dto.request.product.ProductRequest
import com.kire.market_place_android.data.remote.dto.response.product.ProductResponse

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType

import javax.inject.Inject

class ProductApi @Inject constructor(
    private val client: HttpClient
): IProductApi {

    // get all products presented in the store
    override suspend fun getAllProducts(): List<ProductResponse> {
        return client.get {
            url(HttpRoutes.GET_ALL_PRODUCTS)
            contentType(ContentType.Application.Json)
        }.body<List<ProductResponse>>()
    }

    // get all products' categories
    // like 'fruits', 'vegetables', 'electronic' and so on
    override suspend fun getAllAvailableCategories(): Set<String> {
        return client.get {
            url(HttpRoutes.GET_ALL_AVAILABLE_CATEGORIES)
            contentType(ContentType.Application.Json)
        }.body<Set<String>>()
    }

    // update product info by its id
    override suspend fun updateProductById(id: Int, product: ProductRequest) {
        client.delete {
            url(HttpRoutes.DELETE_PRODUCT_BY_ID + id.toString())
        }
    }

    // add new product to the store
    override suspend fun addProduct(product: ProductRequest) {
        client.post {
            url(HttpRoutes.ADD_PRODUCT)
            contentType(ContentType.MultiPart.FormData)
            setBody(
                MultiPartFormDataContent(
                    formData {
                        append("title", product.title)
                        append("price", product.price.toString())
                        append("unit", product.unit)
                        append("discount_price", product.discountPrice.toString())
                        append("description", product.description)
                        append("quantity_of_available", product.quantityOfAvailable.toString())
                        append("category", product.category)
                        append("image", product.image, Headers.build {
                            append(HttpHeaders.ContentType, "image/jpeg")
                            append(HttpHeaders.ContentDisposition, "filename=${product.title}.jpg")
                        })
                    }
                )
            )
        }
    }
}
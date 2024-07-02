package com.kire.market_place_android.domain.model.auth

sealed class AuthResultDomain<T>(val data: T? = null) {
    class Authorized<T>(data: T? = null): AuthResultDomain<T>(data)
    class Unauthorized<T>: AuthResultDomain<T>()
    class UnknownError<T>(data: T? = null): AuthResultDomain<T>(data)
}
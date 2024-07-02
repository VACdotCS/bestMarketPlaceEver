package com.kire.market_place_android.domain.model

sealed interface IRequestResultDomain {
    class Success<T>(val data: T): IRequestResultDomain
    object SuccessfullyDone: IRequestResultDomain
    class Errors(val messages: List<String?>): IRequestResultDomain
    object Idle: IRequestResultDomain
}
package com.kire.market_place_android.presentation.model

sealed interface IRequestResult {
    class Success<T>(val data: T): IRequestResult
    object SuccessfullyDone: IRequestResult
    class Errors(val messages: List<String?>): IRequestResult
    object Idle: IRequestResult
}
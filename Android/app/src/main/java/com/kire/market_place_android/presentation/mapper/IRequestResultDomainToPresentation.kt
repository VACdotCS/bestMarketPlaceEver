package com.kire.market_place_android.presentation.mapper

import com.kire.market_place_android.domain.model.IRequestResultDomain
import com.kire.market_place_android.presentation.model.IRequestResult

fun <T> IRequestResultDomain.toPresentation() =
    when(this) {
        is IRequestResultDomain.Success<*> -> {
            @Suppress("UNCHECKED_CAST")
            IRequestResult.Success(this.data as T)
        }

        IRequestResultDomain.SuccessfullyDone ->
            IRequestResult.SuccessfullyDone

        IRequestResultDomain.Idle ->
            IRequestResult.Idle

        is IRequestResultDomain.Errors ->
            IRequestResult.Errors(this.messages)
    }
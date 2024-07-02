package com.kire.market_place_android.presentation.mapper

import com.kire.market_place_android.domain.model.IRequestResultDomain
import com.kire.market_place_android.presentation.model.IRequestResult

fun <T> IRequestResult.toDomain() =
    when(this) {
        is IRequestResult.Success<*> -> {
            @Suppress("UNCHECKED_CAST")
            IRequestResultDomain.Success(this.data as T)
        }

        IRequestResult.SuccessfullyDone ->
            IRequestResultDomain.SuccessfullyDone

        IRequestResult.Idle ->
            IRequestResultDomain.Idle

        is IRequestResult.Errors ->
            IRequestResultDomain.Errors(this.messages)
    }
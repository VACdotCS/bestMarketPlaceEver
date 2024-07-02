package com.kire.market_place_android.data.remote.dto

import kotlinx.serialization.Serializable

//import java.lang.Exception
//import kotlin.Exception
//
//data class Error(
//    override val message: String
//): Exception()

@Serializable
data class Errors(
    val errors: List<String> = emptyList()
): Exception()
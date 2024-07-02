package com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext

import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.model.IRequestResult

import kotlinx.coroutines.flow.StateFlow

@Composable
fun RequestResultMessage(
    requestResultStateFlow: StateFlow<IRequestResult>,
    makeRequestResultIdle: () -> Unit
){
    val requestResult by requestResultStateFlow.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(requestResult) {
        when(requestResult) {
            is IRequestResult.Errors -> {
                val errors = (requestResult as IRequestResult.Errors).messages.map {
                    it.toString()
                }
                if (errors.isNotEmpty())
                    errors.forEach { error ->
                        Toast.makeText(
                            context,
                            error,
                            LENGTH_SHORT
                        ).show()
                    }
                else
                    Toast.makeText(
                        context,
                        Strings.SOME_ERROR,
                        LENGTH_SHORT
                    ).show()
            }
            IRequestResult.SuccessfullyDone -> {
                Toast.makeText(
                    context,
                    Strings.SUCCESS,
                    LENGTH_SHORT
                ).show()
            }
            else -> {

            }
        }

        makeRequestResultIdle()
    }
}
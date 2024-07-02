package com.kire.market_place_android.presentation.ui.screen.common

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.kire.market_place_android.domain.model.auth.AuthResultDomain
import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.model.IRequestResult

import com.kire.market_place_android.presentation.model.auth.AuthUiEvent
import com.kire.market_place_android.presentation.navigation.transition.auth.LogInScreenTransitions
import com.kire.market_place_android.presentation.ui.screen.destinations.LogOnScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.ShoppingScreenDestination
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.market_place_android.presentation.util.Validator
import com.kire.market_place_android.presentation.viewmodel.AuthViewModel

import com.kire.test.R

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

/**
 * Экран авторизации пользователя
 *
 * @param authViewModel ViewModel авторизации
 * @param navigator для навигации между экранами
 * @param paddingValues отступы от краёв экрана
 *
 * @author Michael Gontarev (KiREHwYE)*/
@Destination(start = true, style = LogInScreenTransitions::class)
@Composable
fun LogInScreen(
    authViewModel: AuthViewModel,
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(52.dp)
){
    val context = LocalContext.current

    val authState = authViewModel.authState

    LaunchedEffect(authViewModel, context) {
        authViewModel.authResultChannel.collect { result ->

            when(result) {
                is AuthResultDomain.Authorized -> {
                    Toast.makeText(
                        context,
                        Strings.LOGIN_SUCCESSFUL,
                        LENGTH_SHORT
                    ).show()
                    navigator.navigate(ShoppingScreenDestination) {
                        popUpTo(ShoppingScreenDestination) {
                            inclusive = true
                        }
                    }
                }
                is AuthResultDomain.Unauthorized -> {
                    Toast.makeText(
                        context,
                        Strings.LOGIN_UNSUCCESSFUL,
                        LENGTH_SHORT
                    ).show()
                }
                is AuthResultDomain.UnknownError -> {
                    val errors = (result.data as List<*>).map {
                        it.toString()
                    }
                    errors.forEach { error ->
                        Toast.makeText(
                            context,
                            error,
                            LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ){

        Column(
            verticalArrangement = Arrangement.spacedBy(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = Strings.LOGIN_HEADER,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            Column(
                modifier = Modifier
                    .wrapContentSize(),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {

                BasicTextField(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                        .shadow(
                            elevation = 12.dp,
                            spotColor = ExtendedTheme.colors.redAccent,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .background(Color.White)
                        .padding(16.dp),
                    value = authState.logInPhone,
                    onValueChange = {
                        if (it.length <= 20)
                            authViewModel.onEvent(AuthUiEvent.LogInPhoneChanged(it))
                    },
                    textStyle = LocalTextStyle.current.copy(
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                    ),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.phone_call),
                                contentDescription = null,
                                tint = ExtendedTheme.colors.redAccent,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            Box {
                                if (authState.logInPhone.isEmpty())
                                    Text(
                                        text = Strings.PHONE_HINT,
                                        fontWeight = FontWeight.W400,
                                        color = Color.Gray
                                    )
                                innerTextField()
                            }
                        }
                    }
                )

                BasicTextField(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                        .shadow(
                            elevation = 12.dp,
                            spotColor = ExtendedTheme.colors.redAccent,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .background(Color.White)
                        .padding(16.dp),
                    value = authState.logInPassword,
                    onValueChange = {
                        if (it.length <= 100)
                            authViewModel.onEvent(AuthUiEvent.LogInPasswordChanged(it))
                    },
                    textStyle = LocalTextStyle.current.copy(
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                    ),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.key),
                                contentDescription = null,
                                tint = ExtendedTheme.colors.redAccent,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            Box {
                                if (authState.logInPassword.isEmpty())
                                    Text(
                                        text = Strings.PASSWORD_HINT,
                                        fontWeight = FontWeight.W400,
                                        color = Color.Gray
                                    )
                                innerTextField()
                            }
                        }
                    }
                )
            }

            Column(
                modifier = Modifier
                    .wrapContentSize(),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    onClick = {
                        try {
                            Validator.validatePhone(authState.logInPhone)
                            Validator.validatePassword(authState.logInPassword)

                            authViewModel.onEvent(AuthUiEvent.LogIn)

                        } catch (e: IllegalArgumentException) {
                            Toast.makeText(context, e.message, LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ExtendedTheme.colors.redAccent
                    )
                ) {
                    Text(
                        text = Strings.LOGIN_BUTTON,
                        fontSize = 16.sp
                    )
                }

                Text(
                    text = Strings.LOGON_SUGGESTION,
                    fontWeight = FontWeight.W400,
                    color = Color.Gray,
                    modifier = Modifier
                        .drawBehind {
                            val strokeWidthPx = 1.dp.toPx()
                            val verticalOffset = size.height - 1.sp.toPx()
                            drawLine(
                                color = Color.Gray,
                                strokeWidth = strokeWidthPx,
                                start = Offset(0f, verticalOffset),
                                end = Offset(size.width, verticalOffset)
                            )
                        }
                        .pointerInput(Unit) {
                            detectTapGestures {
                                navigator.navigate(LogOnScreenDestination)
                            }
                        }
                )
            }
        }
    }
}
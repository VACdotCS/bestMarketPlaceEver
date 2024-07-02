package com.kire.market_place_android.presentation.ui.screen.common

import android.widget.Toast

import androidx.activity.compose.BackHandler

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

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
import com.kire.market_place_android.presentation.model.auth.AuthUiEvent
import com.kire.market_place_android.presentation.navigation.transition.auth.LogOnScreenTransitions
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
 * Экран регистрации пользователя
 *
 * @param authViewModel ViewModel для авторизации
 * @param navigator для навигации между экранами
 * @param paddingValues отступы от краев экрана
 *
 * @author Michael Gontarev (KiREHwYE)*/
@Destination(style = LogOnScreenTransitions::class)
@Composable
fun LogOnScreen(
    authViewModel: AuthViewModel,
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(52.dp)
){

    BackHandler {
        navigator.popBackStack(route = LogOnScreenDestination.route, inclusive = true)
    }

    val context = LocalContext.current

    val authState = authViewModel.authState

    LaunchedEffect(authViewModel, context) {
        authViewModel.authResultChannel.collect { result ->

            when(result) {
                is AuthResultDomain.Authorized -> {
                    Toast.makeText(
                        context,
                        Strings.LOGON_SUCCESSFUL,
                        Toast.LENGTH_SHORT
                    ).show()
                    navigator.navigate(ShoppingScreenDestination) {
                        popUpTo(LogOnScreenDestination) {
                            inclusive = true
                        }
                    }

                }
                is AuthResultDomain.Unauthorized -> {
                    Toast.makeText(
                        context,
                        Strings.LOGON_UNSUCCESSFUL,
                        Toast.LENGTH_SHORT
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
                            Toast.LENGTH_SHORT
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
                text = Strings.LOGON_HEADER,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            Column(
                modifier = Modifier
                    .wrapContentSize(),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {

                LogOnInputItem(
                    value = authState.logOnName,
                    icon = R.drawable.user,
                    hint = Strings.NAME_HINT,
                    onEvent = {
                        authViewModel.onEvent(AuthUiEvent.LogOnNameChanged(it))
                    }
                )

                LogOnInputItem(
                    value = authState.logOnPhone,
                    icon = R.drawable.phone_call,
                    hint = Strings.PHONE_HINT,
                    onEvent = {
                        authViewModel.onEvent(AuthUiEvent.LogOnPhoneChanged(it))
                    }
                )

                LogOnInputItem(
                    value = authState.logOnEmail,
                    icon = R.drawable.accept_email,
                    hint = Strings.EMAIL_HINT,
                    onEvent = {
                        authViewModel.onEvent(AuthUiEvent.LogOnEmailChanged(it))
                    }
                )

                LogOnInputItem(
                    value = authState.logOnPassword,
                    icon = R.drawable.key,
                    hint = Strings.PASSWORD_HINT,
                    onEvent = {
                        authViewModel.onEvent(AuthUiEvent.LogOnPasswordChanged(it))
                    }
                )

                LogOnInputItem(
                    value = authState.logOnRepeatedPassword,
                    icon = R.drawable.double_key,
                    hint = Strings.PASSWORD_REPEAT_HINT,
                    onEvent = {
                        authViewModel.onEvent(AuthUiEvent.LogOnRepeatedPasswordChanged(it))
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
                            Validator.validateName(authState.logOnName)
                            Validator.validatePhone(authState.logOnPhone)
                            Validator.validateEmail(authState.logOnEmail)
                            Validator.validatePassword(authState.logOnPassword)
                            Validator.validateRepeatedPassword(authState.logOnPassword, authState.logOnRepeatedPassword)

                            authViewModel.onEvent(AuthUiEvent.LogOn)
                        } catch (e: IllegalArgumentException) {
                            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
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
                        text = Strings.LOGON_BUTTON,
                        fontSize = 16.sp
                    )
                }

                Text(
                    text = Strings.LOGIN_SUGGESTION,
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
                                navigator.popBackStack(route = LogOnScreenDestination.route, inclusive = true)
                            }
                        }
                )
            }
        }
    }
}

@Composable
fun LogOnInputItem(
    value: String,
    @DrawableRes icon: Int,
    hint: String,
    onEvent: (String) -> Unit
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
        value = value,
        onValueChange = {
            onEvent(it)
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
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = ExtendedTheme.colors.redAccent,
                    modifier = Modifier
                        .size(24.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (value.isEmpty())
                        Text(
                            text = hint,
                            fontWeight = FontWeight.W400,
                            color = Color.Gray
                        )
                    innerTextField()
                }
            }
        }
    )
}
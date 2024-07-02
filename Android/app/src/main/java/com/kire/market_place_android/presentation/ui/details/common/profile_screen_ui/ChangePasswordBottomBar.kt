package com.kire.market_place_android.presentation.screen.profile_screen_ui

import android.widget.Toast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.constant.Strings

import com.kire.market_place_android.presentation.model.user.ProfileState
import com.kire.market_place_android.presentation.model.user.UserUiEvent
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.market_place_android.presentation.util.Validator

import com.kire.test.R

/**
 * Нижний бар смены пароля
 *
 * @param profileState состояние ui экрана профиля пользователя
 * @param showBottomSheet показать/скрыть нижний бар
 * @param onEvent обрабатывать события
 * @param sheetState состояние нижнего бара
 *
 * @author Michael Gontarev (KiREHwYE)*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordBottomBar(
    profileState: ProfileState,
    showBottomSheet: (Boolean) -> Unit,
    onEvent: (UserUiEvent) -> Unit,
    sheetState: SheetState
){

    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = {
            showBottomSheet(false)
        },
        containerColor = Color.White,
        sheetState = sheetState,
        dragHandle = {
            Text(
                text = Strings.CHANGE_PASSWORD_TITLE,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 28.dp)
            )
        },
        modifier = Modifier
            .height(IntrinsicSize.Max)

    ) {

        Box(
            modifier = Modifier
                .wrapContentHeight()
                .padding(
                    top = 28.dp,
                    start = 36.dp,
                    end = 36.dp,
                    bottom = 28.dp
                ),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(36.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .wrapContentSize(),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {

                    BasicTextField(
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(ExtendedTheme.colors.profileBar)
                            .padding(16.dp),
                        value = profileState.currentPassword,
                        onValueChange = {
                            onEvent(UserUiEvent.CurrentPasswordChanged(it))
                        },

                        textStyle = LocalTextStyle.current.copy(
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
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
                                    if (profileState.currentPassword.isEmpty())
                                        Text(
                                            text = Strings.OLD_PASSWORD_HINT,
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
                            .clip(RoundedCornerShape(12.dp))
                            .background(ExtendedTheme.colors.profileBar)
                            .padding(16.dp),
                        value = profileState.newPassword,
                        onValueChange = {
                            onEvent(UserUiEvent.NewPasswordChanged(it))
                        },

                        textStyle = LocalTextStyle.current.copy(
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
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
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                                Box {
                                    if (profileState.newPassword.isEmpty())
                                        Text(
                                            text = Strings.NEW_PASSWORD_HINT,
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
                            .clip(RoundedCornerShape(12.dp))
                            .background(ExtendedTheme.colors.profileBar)
                            .padding(16.dp),
                        value = profileState.confirmationPassword,
                        onValueChange = {
                            onEvent(UserUiEvent.ConfirmationPasswordChanged(it))
                        },

                        textStyle = LocalTextStyle.current.copy(
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
                        ),
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.double_key),
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                                Box {
                                    if (profileState.confirmationPassword.isEmpty())
                                        Text(
                                            text = Strings.REPEAT_PASSWORD_HINT,
                                            fontWeight = FontWeight.W400,
                                            color = Color.Gray
                                        )
                                    innerTextField()
                                }
                            }
                        }
                    )
                }

                Button(
                    onClick = {
                        try {
                            Validator.validatePassword(profileState.currentPassword)
                            Validator.validatePassword(profileState.newPassword)
                            Validator.validatePasswordNovelty(profileState.currentPassword, profileState.newPassword)
                            Validator.validateRepeatedPassword(profileState.newPassword, profileState.confirmationPassword)

                            onEvent(UserUiEvent.ChangePassword)

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
                        text = Strings.UPDATE_PASSWORD_SUGGESTION,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
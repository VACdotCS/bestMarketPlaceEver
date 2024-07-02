package com.kire.market_place_android.presentation.screen.admin_panel_pick_up_screen_ui

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.model.admin.AdminPickUpPointState
import com.kire.market_place_android.presentation.model.admin.AdminPickUpPointUiEvent
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.market_place_android.presentation.util.Validator
import com.kire.test.R

/**
 * Всплывающий нижний бар для создания/обновления пункта выдачи
 *
 * @param adminPickUpPointState состояние ui экрана админа
 * @param sheetState состояние нижнего бара
 * @param onEvent обработчик событий
 *
 * @author Aleksey Timko (de4ltt)*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickUpPointBottomBar(
    adminPickUpPointState: AdminPickUpPointState,
    sheetState: SheetState,
    onEvent: (AdminPickUpPointUiEvent) -> Unit
) {

    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = {
            onEvent(AdminPickUpPointUiEvent.ChangeIsCreateBottomBarExpanded(false))
            onEvent(AdminPickUpPointUiEvent.ChangeIsUpdateBottomBarExpanded(false, pickUpPointToUpdateId = "", address = "", managerId = ""))
        },
        containerColor = Color.White,
        sheetState = sheetState,
        dragHandle = {
            Text(
                text = Strings.PICK_UP_POINT,
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
                            .height(86.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(ExtendedTheme.colors.profileBar)
                            .padding(16.dp),
                        value = adminPickUpPointState.bottomBarAddress,
                        onValueChange = {
                            onEvent(AdminPickUpPointUiEvent.bottomBarAddressChanged(it))
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
                                    painter = painterResource(id = R.drawable.house),
                                    contentDescription = null,
                                    tint = ExtendedTheme.colors.redAccent,
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                                Box {
                                    if (adminPickUpPointState.bottomBarAddress.isEmpty())
                                        Text(
                                            text = Strings.ADDRESS,
                                            fontWeight = FontWeight.W400,
                                            color = Color.Gray
                                        )
                                    innerTextField()
                                }
                            }
                        }
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = Strings.MANAGER_ID,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )

                        BasicTextField(
                            modifier = Modifier
                                .height(56.dp)
                                .width(110.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(ExtendedTheme.colors.profileBar)
                                .padding(16.dp),
                            value = adminPickUpPointState.bottomBarManagerId,
                            onValueChange = {
                                onEvent(AdminPickUpPointUiEvent.bottomBarManagerIdChanged(it))
                            },
                            textStyle = LocalTextStyle.current.copy(
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W400,
                                textAlign = TextAlign.Center
                            ),
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    innerTextField()
                                    if (adminPickUpPointState.bottomBarManagerId.isEmpty())
                                        Text(
                                            modifier = Modifier.fillMaxHeight(),
                                            text = Strings.XXX,
                                            fontWeight = FontWeight.W400,
                                            color = Color.Gray
                                        )
                                }

                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                                try {
                                    Validator.validateManagerId(adminPickUpPointState.bottomBarManagerId)
                                    Validator.validatePickUpPointAddress(adminPickUpPointState.bottomBarAddress)

                                    if (adminPickUpPointState.isCreateBottomBarExpanded)
                                        onEvent(
                                            AdminPickUpPointUiEvent.CreatePickUpPoint(
                                                managerId = adminPickUpPointState.bottomBarManagerId.toInt(),
                                                address = adminPickUpPointState.bottomBarAddress
                                            )
                                        )
                                    else {
                                        Validator.validatePickUpPointToUpdateId(adminPickUpPointState.pickUpPointToUpdateId)
                                        onEvent(
                                            AdminPickUpPointUiEvent.UpdatePickUpPoint(
                                                pickUpPointToUpdateId = adminPickUpPointState.pickUpPointToUpdateId.toInt(),
                                                managerId = adminPickUpPointState.bottomBarManagerId.toInt(),
                                                address = adminPickUpPointState.bottomBarAddress
                                            )
                                        )
                                    }
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
                            text =
                                if (adminPickUpPointState.isCreateBottomBarExpanded)
                                    Strings.CREATE_PICK_UP_POINT
                                else
                                    Strings.UPDATE_PICK_UP_POINT,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
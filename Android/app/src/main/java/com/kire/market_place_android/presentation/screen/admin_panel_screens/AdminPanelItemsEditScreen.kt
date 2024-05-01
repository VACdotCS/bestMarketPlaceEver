package com.kire.market_place_android.presentation.screen.admin_panel_screens

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kire.market_place_android.presentation.model.ProductItem
import com.kire.market_place_android.presentation.screen.admin_panel_items_edit_screen_ui.AdminPanelIconField
import com.kire.market_place_android.presentation.screen.destinations.ProfileScreenDestination
import com.kire.market_place_android.presentation.screen.item_add_to_cart_menu.BottomButtonFinishOperation
import com.kire.test.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AdminPanelItemsEditScreen(
    productItem: ProductItem = ProductItem(
        "Помидоры", "250.00",
        "кг", "250.00",
        Uri.EMPTY, false,
        "Помогите, они украли мою семью..."
    ),
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(28.dp)
) {
    BackHandler {
        navigator.popBackStack(ProfileScreenDestination, inclusive = true)
        return@BackHandler
    }

    productItem.apply {
        //TODO

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(430.dp),
                content = {
                    AsyncImage(
                        model = Bitmap.createBitmap(500, 430, Bitmap.Config.RGB_565),
                        placeholder = painterResource(id = R.drawable.item_menu_default),
                        contentDescription = "Item Cart Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(430.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = 45.dp,
                                start = 20.dp,
                                end = 20.dp,
                                bottom = 35.dp
                            ),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(55.dp)
                                    .background(
                                        Color.White,
                                        RoundedCornerShape(30.dp)
                                    ),
                                contentAlignment = Alignment.Center,
                                content = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.arrow_back),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(27.dp)
                                            .pointerInput(Unit) {
                                                detectTapGestures {
                                                    navigator.popBackStack()
                                                }
                                            },
                                        tint = Color.Black
                                    )
                                }
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.update_sign),
                                contentDescription = "update_sign"
                            )
                        }
                    }


                }
            )

            Column(
                modifier = Modifier
                    .padding(top = 405.dp)
                    .fillMaxSize()
                    .background(
                        Color.White,
                        RoundedCornerShape(
                            topStart = 25.dp,
                            topEnd = 25.dp
                        )
                    ),
                content = {

                    Column(
                        modifier = Modifier
                            .padding(paddingValues),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        AdminPanelIconField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            icon = R.drawable.name_icon,
                            hintText = R.string.enter_name
                        )

                        Row{

                            AdminPanelIconField(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(end = 5.dp)
                                    .height(60.dp),
                                icon = R.drawable.rub,
                                hintText = R.string.rub_num,
                                isTextCentered = true
                            )

                            AdminPanelIconField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp)
                                    .height(60.dp),
                                icon = R.drawable.scale,
                                hintText = R.string.scale,
                                isTextCentered = true
                            )
                        }

                        Row{

                            AdminPanelIconField(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(end = 5.dp)
                                    .height(60.dp),
                                icon = R.drawable.rub_proc,
                                hintText = R.string.rub_num,
                                isTextCentered = true
                            )

                            AdminPanelIconField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp)
                                    .height(60.dp),
                                icon = R.drawable.container,
                                hintText = R.string.contains,
                                isTextCentered = true
                            )
                        }

                        AdminPanelIconField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp),
                            icon = null,
                            hintText = R.string.description,
                            isTextCentered = false
                        )
                    }

                    Spacer(modifier = Modifier.size(7.dp))

                    BottomButtonFinishOperation(
                        textValue = stringResource(id = R.string.save),
                        onClick = {  /* TODO */ }
                    )

                    Spacer(modifier = Modifier.size(15.dp))

                }
            )
        }

}
package com.kire.market_place_android.presentation.ui.screen.admin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult

import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import java.io.ByteArrayOutputStream

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kire.market_place_android.presentation.constant.ImagePath

import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.navigation.transition.admin.AdminPanelItemsEditScreenTransitions
import com.kire.market_place_android.presentation.ui.details.admin.admin_panel_items_edit_screen_ui.AdminEditTopControls
import com.kire.market_place_android.presentation.ui.details.admin.admin_panel_items_edit_screen_ui.AdminPanelIconField
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.RequestResultMessage
import com.kire.market_place_android.presentation.ui.details.common.item_add_to_cart_menu_ui.BottomButtonFinishOperation
import com.kire.market_place_android.presentation.ui.screen.destinations.AdminPanelItemsEditScreenDestination
import com.kire.market_place_android.presentation.util.compressImage
import com.kire.market_place_android.presentation.viewmodel.ProductViewModel

import com.kire.test.R

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


import java.io.InputStream

/**
 * Функция конвертации входящего потока байт в массив
 *
 * @param inputStream поток байт
 *
 * @author Michael Gontarev (KiREHwYE)*/
private suspend fun getBytes(inputStream: InputStream): ByteArray {
    return withContext(Dispatchers.IO) {
        // Decode the input stream into a Bitmap
        val bitmap = BitmapFactory.decodeStream(inputStream)

        // Initialize variables for compression
        var quality = 100
        var byteArray: ByteArray
        val byteBuffer = ByteArrayOutputStream()

        // Compress the Bitmap and check the size
        do {
            byteBuffer.reset() // Clear the buffer
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteBuffer)
            byteArray = byteBuffer.toByteArray()
            quality -= 5 // Decrease the quality by 5 for the next iteration
        } while (byteArray.size > 1024 * 1024 && quality > 0)

        // Release the bitmap to free up memory
        bitmap.recycle()


        byteArray
    }
}

/**
 * Экран редактирования информации о товаре
 *
 * @param adminViewModel ViewModel админа
 * @param navigator для навигации между экранами
 * @param paddingValues отступы от краев экрана
 *
 * @author Michael Gontarev (KiREHwYE)
 * @author Aleksey Timko (de4ltt)*/
@Destination(style = AdminPanelItemsEditScreenTransitions::class)
@Composable
fun AdminPanelItemsEditScreen(
    productViewModel: ProductViewModel,
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(28.dp)
) {
    BackHandler {
        navigator.popBackStack()
        return@BackHandler
    }

    val product by productViewModel.chosenProduct.collectAsStateWithLifecycle()

    val image = rememberSaveable {
        mutableStateOf(byteArrayOf())
    }

    RequestResultMessage(
        requestResultStateFlow = productViewModel.requestResult,
        makeRequestResultIdle = productViewModel::makeRequestResultIdle
    )

    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { imageUri ->

            if (imageUri == null)
                return@rememberLauncherForActivityResult

            coroutineScope.launch {
                val inputStream: InputStream = context.contentResolver.openInputStream(imageUri)
                    ?: return@launch
                
                image.value = getBytes(inputStream)

                inputStream.close()
            }
        }
    )

    var itemName by remember {
        mutableStateOf(product.title)
    }

    var itemCategory by remember {
        mutableStateOf(product.category)
    }

    var itemPrice by remember {
        mutableStateOf(product.price.toString())
    }

    var itemDiscountPrice by remember {
        mutableStateOf(product.discountPrice.toString())
    }

    var itemMeasure by remember {
        mutableStateOf(product.unit)
    }

    var itemStored by remember {
        mutableStateOf(product.quantityAvailable.toString())
    }

    var itemDescription by remember {
        mutableStateOf(product.description)
    }

    product.apply {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .pointerInput(Unit) {
                    detectVerticalDragGestures { _, dragAmount ->

                        val y = dragAmount

                        if (y > 60) {
                            navigator.popBackStack(
                                AdminPanelItemsEditScreenDestination,
                                inclusive = true
                            )
                        }
                    }
                },
            verticalArrangement = Arrangement.spacedBy((-24).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Crossfade(
                targetState =
                    if (image.value.isNotEmpty())
                        image.value
                    else product.image,
                animationSpec = tween(durationMillis = 600, easing = LinearEasing),
                label = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it)
                        .build(),
                    placeholder = painterResource(id = R.drawable.item_menu_default),
                    error = painterResource(id = R.drawable.default_image),
                    contentDescription = "Shopping cart item image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .shadow(
                        elevation = 12.dp,
                        spotColor = Color.Gray,
                        ambientColor = Color.Black,
                        shape = RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp
                        )
                    )
                    .background(
                        Color.White,
                        RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp
                        )
                    )
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        AdminPanelIconField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            icon = R.drawable.name_icon,
                            hint = Strings.ENTER_NAME,
                            onTextValueChange = {
                                itemName = it
                            },
                            textValue = itemName
                        )

                        AdminPanelIconField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            icon = R.drawable.tag,
                            hint = Strings.ENTER_CATEGORY,
                            onTextValueChange = {
                                itemCategory = it
                            },
                            textValue = itemCategory
                        )

                        LazyVerticalGrid(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            columns = GridCells.Fixed(count = 2),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            item {
                                AdminPanelIconField(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(60.dp),
                                    icon = R.drawable.rub,
                                    hint = Strings.RUB_NUM,
                                    isTextCentered = true,
                                    onTextValueChange = {
                                        itemPrice = it
                                    },
                                    textValue = itemPrice.toString()
                                )
                            }
                            item {
                                AdminPanelIconField(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(60.dp),
                                    icon = R.drawable.scale,
                                    hint = Strings.SCALE,
                                    isTextCentered = true,
                                    onTextValueChange = {
                                        itemMeasure = it
                                    },
                                    textValue = itemMeasure
                                )
                            }

                            item {
                                AdminPanelIconField(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(60.dp),
                                    icon = R.drawable.rub_proc,
                                    hint = Strings.RUB_NUM,
                                    isTextCentered = true,
                                    onTextValueChange = {
                                        itemDiscountPrice = it
                                    },
                                    textValue = itemDiscountPrice.toString()
                                )
                            }

                            item {
                                AdminPanelIconField(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(60.dp),
                                    icon = R.drawable.container,
                                    hint = Strings.CONTAINS,
                                    isTextCentered = true,
                                    onTextValueChange = {
                                        itemStored = it
                                    },
                                    textValue = itemStored.toString()
                                )
                            }
                        }

                        AdminPanelIconField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp),
                            icon = null,
                            hint = Strings.DESCRIPTION,
                            isTextCentered = false,
                            onTextValueChange = {
                                itemDescription = it
                            },
                            textValue = itemDescription
                        )
                    }
                }

                BottomButtonFinishOperation(
                    textValue = if (product.id == -1)
                        Strings.SAVE else Strings.DELETE,
                    onClick = {
                        coroutineScope.launch {
                            if (product.id == -1)
                                productViewModel.addProduct(
                                    image = image.value,
                                    product = product.copy(
                                        title = itemName,
                                        category = itemCategory,
                                        price = itemPrice.toBigDecimal(),
                                        discountPrice = itemDiscountPrice.toBigDecimal(),
                                        unit = itemMeasure,
                                        quantityAvailable = itemStored.toInt(),
                                        description = itemDescription
                                    )
                                )
                            else productViewModel.updateProductById(
                                id = product.id,
                                image = image.value,
                                product = product.copy(
                                    title = itemName,
                                    category = itemCategory,
                                    price = itemPrice.toBigDecimal(),
                                    discountPrice = itemDiscountPrice.toBigDecimal(),
                                    unit = itemMeasure,
                                    quantityAvailable = itemStored.toInt(),
                                    description = itemDescription
                                )
                            )
                            productViewModel.refreshProducts()
                        }
                    }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopStart
        ) {
            AdminEditTopControls(
                onArrowBackClick = {
                    navigator.popBackStack()
                },
                onUploadButtonClick = {
                    galleryLauncher.launch("image/*")
                }
            )
        }
    }
}

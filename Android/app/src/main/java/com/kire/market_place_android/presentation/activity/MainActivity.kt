package com.kire.market_place_android.presentation.activity

import android.annotation.SuppressLint

import android.app.Activity

import android.os.Build
import android.os.Bundle

import android.view.Window
import android.view.WindowManager
import android.widget.Toast

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.model.user.Role

import com.kire.market_place_android.presentation.navigation.NavigationUI
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.BottomBar
import com.kire.market_place_android.presentation.ui.screen.destinations.LogInScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.ShoppingScreenDestination
import com.kire.market_place_android.presentation.ui.theme.MarketExtendedTheme
import com.kire.market_place_android.presentation.viewmodel.AdminViewModel
import com.kire.market_place_android.presentation.viewmodel.AuthViewModel
import com.kire.market_place_android.presentation.viewmodel.ManagerViewModel
import com.kire.market_place_android.presentation.viewmodel.OrderViewModel
import com.kire.market_place_android.presentation.viewmodel.ProductViewModel
import com.kire.market_place_android.presentation.viewmodel.UserViewModel
import com.kire.test.R

import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.popUpTo

import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.runBlocking

/**
 * @author Michael Gontarev (KiREHwYE)
 * @author Aleksey Timko (de4ltt)*/
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    private val adminViewModel: AdminViewModel by viewModels()
    private val managerViewModel: ManagerViewModel by viewModels()
    private val productViewModel: ProductViewModel by viewModels()
    private val orderViewModel: OrderViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.hideSystemUi(extraAction = {
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        })
        setDisplayCutoutMode()

        runBlocking {
            if (authViewModel.isTokenExpired()) {
                userViewModel.logOut()
                Toast.makeText(
                    this@MainActivity,
                    Strings.NON_AUTHORIZED,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        setContent {
            MarketExtendedTheme {

                val navHostEngine = rememberAnimatedNavHostEngine(navHostContentAlignment = Alignment.TopCenter)
                val navHostController = navHostEngine.rememberNavController()

                val role by userViewModel.role.collectAsStateWithLifecycle()
                val isAuthenticated by authViewModel.isAuthenticated.collectAsStateWithLifecycle()

                LaunchedEffect(isAuthenticated) {
                    if (isAuthenticated) {
                        navHostController.navigate(ShoppingScreenDestination.route) {
                            popUpTo(LogInScreenDestination) {
                                inclusive = true
                            }
                        }

                        productViewModel.refreshProducts()
                        productViewModel.getAllCategories()
                        userViewModel.getAllPickUpPoints()
                        userViewModel.updateUser()
                        orderViewModel.getOrders()

                        if (role == Role.MANAGER)
                            managerViewModel.getPickUpPointByManagerId(userViewModel.userId.value)
                    }
                }

                Scaffold(
                    bottomBar = {
                        BottomBar(
                            navHostController = navHostController,
                            cartState = productViewModel.cartState,
                            role = role
                        )
                    }

                ) {  _ ->

                    NavigationUI(
                        authViewModel = authViewModel,
                        userViewModel = userViewModel,
                        adminViewModel = adminViewModel,
                        managerViewModel = managerViewModel,
                        productViewModel = productViewModel,
                        orderViewModel = orderViewModel,
                        navHostController = navHostController,
                        navHostEngine = navHostEngine
                    )
                }
            }
        }
    }
}

private fun Window.hideSystemUi(extraAction:(WindowInsetsControllerCompat.() -> Unit)? = null) {
    WindowInsetsControllerCompat(this, this.decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        extraAction?.invoke(controller)
    }
}

internal fun Activity.setDisplayCutoutMode() {
    when {
        Build.VERSION.SDK_INT < Build.VERSION_CODES.R -> {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
        }
    }
}
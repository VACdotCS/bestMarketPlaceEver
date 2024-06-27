package com.kire.market_place_android.presentation.ui.screen.admin

import android.widget.Toast

import androidx.activity.compose.BackHandler

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.kire.market_place_android.presentation.model.admin.IAdminResult
import com.kire.market_place_android.presentation.navigation.transition.admin.AdminPanelUsersScreenTransitions
import com.kire.market_place_android.presentation.navigation.util.AppDestinations
import com.kire.market_place_android.presentation.screen.admin_panel_users_screen_ui.AdminUserBar
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.ListWithTopAndFab
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.TopBar
import com.kire.market_place_android.presentation.viewmodel.AdminViewModel
import com.kire.test.R

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Список всех пользователей KubMarket
 *
 * @param adminViewModel ViewModel админа
 * @param horizontalPaddingValues отступы от краев экрана
 * @param navigator для навигации между экранами
 *
 * @author Michael Gontarev (KiREHwYE)
 * @author Aleksey Timko (de4ltt)*/
@Destination(style = AdminPanelUsersScreenTransitions::class)
@Composable
fun AdminPanelUsersScreen(
    adminViewModel: AdminViewModel,
    horizontalPaddingValues: PaddingValues = PaddingValues(horizontal = 28.dp),
    navigator: DestinationsNavigator
) {

    BackHandler {
        navigator.popBackStack()
        return@BackHandler
    }

    val context = LocalContext.current

    val allUsers by adminViewModel.allUsers.collectAsStateWithLifecycle()

    val adminResult by adminViewModel.adminResult.collectAsStateWithLifecycle()

    LaunchedEffect(adminResult) {
        if (adminResult is IAdminResult.Error)
            Toast.makeText(
                context,
                if ((adminResult as IAdminResult.Error).message?.isNotEmpty() == true)
                    (adminResult as IAdminResult.Error).message
                else context.getText(R.string.some_error),
                Toast.LENGTH_SHORT
            ).show()
    }

    ListWithTopAndFab(
        listSize = allUsers.size,
        topBar = {
            TopBar(destination = AppDestinations.AdminDestinations.ADMIN_PANEL_USERS)
        }
    ) {
        LazyColumn(
            modifier = it,
            contentPadding = PaddingValues(bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(allUsers) { user ->
                AdminUserBar(user = user)
            }
        }
    }
}
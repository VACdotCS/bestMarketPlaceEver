package com.kire.market_place_android.presentation.navigation.Transition

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

import androidx.navigation.NavBackStackEntry
import com.kire.market_place_android.presentation.destinations.AdminPanelItemsScreenDestination
import com.kire.market_place_android.presentation.destinations.AdminPanelPickUpScreenDestination
import com.kire.market_place_android.presentation.destinations.AdminPanelUsersScreenDestination
import com.kire.market_place_android.presentation.destinations.ManagerScreenDestination
import com.kire.market_place_android.presentation.destinations.ShoppingCartScreenDestination

import com.ramcosta.composedestinations.spec.DestinationStyle
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.ramcosta.composedestinations.utils.route

object AdminPanelScreenTransitions : DestinationStyle.Animated {

    private val destinationsOnRight: List<DirectionDestinationSpec> =
        listOf(
            ShoppingCartScreenDestination,
            ManagerScreenDestination,
            AdminPanelItemsScreenDestination,
            AdminPanelPickUpScreenDestination,
            AdminPanelUsersScreenDestination
        )

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return if (destinationsOnRight.contains(initialState.route()))
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            ) + fadeIn(animationSpec = tween(300, easing = LinearOutSlowInEasing))
        else
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            ) + fadeIn(animationSpec = tween(300, easing = LinearOutSlowInEasing))
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return if (destinationsOnRight.contains(targetState.route()))
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            )+ fadeOut(animationSpec = tween(300, easing = LinearOutSlowInEasing))
        else
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            ) + fadeOut(animationSpec = tween(300, easing = LinearOutSlowInEasing))
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition(): EnterTransition {
        return if (destinationsOnRight.contains(initialState.route()))
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            )+ fadeIn(animationSpec = tween(300, easing = LinearOutSlowInEasing))
        else
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            ) + fadeIn(animationSpec = tween(300, easing = LinearOutSlowInEasing))
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition(): ExitTransition {
        return if (destinationsOnRight.contains(targetState.route()))
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            )+ fadeOut(animationSpec = tween(300, easing = LinearOutSlowInEasing))
        else
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            ) + fadeOut(animationSpec = tween(300, easing = LinearOutSlowInEasing))
    }
}
package com.kire.market_place_android.presentation.navigation.transition.admin

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry
import com.kire.market_place_android.presentation.ui.screen.destinations.AdminPanelItemsEditScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.Destination
import com.kire.market_place_android.presentation.ui.screen.destinations.ItemAddToCartMenuDestination
import com.ramcosta.composedestinations.spec.DestinationStyle
import com.ramcosta.composedestinations.utils.route

/**
 * By Michael Gontarev (KiREHwYE)*/
object AdminPanelItemsScreenTransitions : DestinationStyle.Animated {

    val withoutTransitions = listOf<Destination>(
        AdminPanelItemsEditScreenDestination, ItemAddToCartMenuDestination
    )

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return if (withoutTransitions.contains(initialState.route()))
            EnterTransition.None
        else
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)) +
                fadeIn(animationSpec = tween(300, easing = LinearOutSlowInEasing))
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return if (withoutTransitions.contains(targetState.route()))
            fadeOut(animationSpec = tween(2000, easing = LinearOutSlowInEasing))
        else
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)) +
                    fadeOut(animationSpec = tween(300, easing = LinearOutSlowInEasing))
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition(): EnterTransition {
        return if (withoutTransitions.contains(initialState.route()))
            EnterTransition.None
        else
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)) +
                    fadeIn(animationSpec = tween(300, easing = LinearOutSlowInEasing))
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition(): ExitTransition {
        return if (withoutTransitions.contains(targetState.route()))
            fadeOut(animationSpec = tween(2000, easing = LinearOutSlowInEasing))
        else
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)) +
                    fadeOut(animationSpec = tween(300, easing = LinearOutSlowInEasing))
    }
}
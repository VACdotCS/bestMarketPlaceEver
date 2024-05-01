package com.kire.market_place_android.presentation.navigation.Transition

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle

object AdminPanelUsersScreenTransitions : DestinationStyle.Animated {

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)) +
                fadeIn(animationSpec = tween(300, easing = LinearOutSlowInEasing))
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)) +
                fadeOut(animationSpec = tween(300, easing = LinearOutSlowInEasing))
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition(): EnterTransition {
        return slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing))+
                fadeIn(animationSpec = tween(300, easing = LinearOutSlowInEasing))
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition(): ExitTransition {
        return slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)) +
                fadeOut(animationSpec = tween(300, easing = LinearOutSlowInEasing))
    }
}
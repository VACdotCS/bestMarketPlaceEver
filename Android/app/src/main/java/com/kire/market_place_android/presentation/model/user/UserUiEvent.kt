package com.kire.market_place_android.presentation.model.user

import com.kire.market_place_android.presentation.model.pick_up_point.PickUpPoint

sealed class UserUiEvent {

    data class UsernameChanged(val value: String): UserUiEvent()
    data class PhoneChanged(val value: String): UserUiEvent()
    data class EmailChanged(val value: String): UserUiEvent()
    object ChangeUserInfo: UserUiEvent()

    data class CardNumberChanged(val value: String): UserUiEvent()
    data class CvcChanged(val value: String): UserUiEvent()
    data class ValidityChanged(val value: String): UserUiEvent()
    object ChangeCard: UserUiEvent()

    data class CurrentPasswordChanged(val value: String): UserUiEvent()
    data class NewPasswordChanged(val value: String): UserUiEvent()
    data class ConfirmationPasswordChanged(val value: String): UserUiEvent()
    object ChangePassword: UserUiEvent()

    data class ChoosePickUpPoint(val chosenPickUpPoint: PickUpPoint): UserUiEvent()
}
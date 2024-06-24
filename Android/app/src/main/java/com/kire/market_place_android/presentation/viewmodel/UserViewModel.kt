package com.kire.market_place_android.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.kire.market_place_android.domain.use_case.common.util.ICommonUseCases
import com.kire.market_place_android.presentation.mapper.user.toPresentation
import com.kire.market_place_android.presentation.mapper.user.toDomain
import com.kire.market_place_android.presentation.model.user.IUserResult
import com.kire.market_place_android.presentation.model.user.Role
import com.kire.market_place_android.presentation.model.user.ProfileState
import com.kire.market_place_android.presentation.model.user.ProfileUiEvent

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val commonUseCases: ICommonUseCases
): ViewModel() {

    private val _role = MutableStateFlow(Role.USER)
    val role: StateFlow<Role> = _role.asStateFlow()

    private val _userId = MutableStateFlow(0)
    val userId: StateFlow<Int> = _userId.asStateFlow()

    private val _userResult: MutableStateFlow<IUserResult> = MutableStateFlow(IUserResult.Idle)
    val userResult: StateFlow<IUserResult> = _userResult.asStateFlow()

    var profileState by mutableStateOf(ProfileState())

    fun onEvent(event: ProfileUiEvent) {
        when(event) {
            is ProfileUiEvent.UsernameChanged -> {
                profileState = profileState.copy(username = event.value)
            }
            is ProfileUiEvent.PhoneChanged -> {
                profileState = profileState.copy(phone = event.value)
            }
            is ProfileUiEvent.EmailChanged -> {
                profileState = profileState.copy(email = event.value)
            }

            ProfileUiEvent.ChangeUserInfo -> {
                viewModelScope.launch {
                    _userResult.value = commonUseCases.changeUserInfoAndReturnUseCase(
                        id = _userId.value,
                        username = profileState.username,
                        phone = profileState.phone,
                        email = profileState.email
                    ).toDomain()
                }
            }

            is ProfileUiEvent.CardNumberChanged -> {
                profileState = profileState.copy(cardNumber = event.value)
            }
            is ProfileUiEvent.CvcChanged -> {
                profileState = profileState.copy(CVC = event.value)
            }
            is ProfileUiEvent.ValidityChanged -> {
                profileState = profileState.copy(validity = event.value)
            }

            ProfileUiEvent.ChangeCard -> {
                viewModelScope.launch {
                    commonUseCases.changeUserCardUseCase(
                        cardNumber = profileState.cardNumber,
                        CVC = profileState.CVC,
                        validity = profileState.validity
                    )
                }
            }

            is ProfileUiEvent.CurrentPasswordChanged -> {
                profileState = profileState.copy(currentPassword = event.value)
            }
            is ProfileUiEvent.NewPasswordChanged -> {
                profileState = profileState.copy(newPassword = event.value)
            }
            is ProfileUiEvent.ConfirmationPasswordChanged -> {
                profileState = profileState.copy(confirmationPassword = event.value)
            }

            ProfileUiEvent.ChangePassword -> {
                viewModelScope.launch {
                    commonUseCases.changePasswordUseCase(
                        currentPassword = profileState.currentPassword,
                        newPassword = profileState.newPassword,
                        confirmationPassword = profileState.confirmationPassword
                    )
                }
            }
        }

    }

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    suspend fun logOut() =
        commonUseCases.logOutUseCase()

    fun updateUser() =
        viewModelScope.launch {
            _userResult.value = commonUseCases.getUserInfoUseCase(_userId.value).toDomain()
                .also {
                    if (it is IUserResult.Success)
                        profileState = profileState.copy(
                            username = it.user.username,
                            phone = it.user.phone,
                            email = it.user.email,
                            cardNumber = it.user.cardNumber ?: "",
                            CVC = it.user.CVC.toString(),
                            validity = it.user.validity?.toString() ?: "",
                            userDiscount = it.user.userDiscount,
                            amountSpent = it.user.amountSpent,
                            redemptionPercent = it.user.redemptionPercent
                        )
                }
        }

    init {
        viewModelScope.launch {
            launch {
                commonUseCases.getRoleUseCase().collect { roleDomain ->
                    _role.value = roleDomain.toPresentation()
                }
            }
            launch {
                commonUseCases.getUserIdUseCase().collect { userId ->
                    _userId.value = userId
                }
            }
            launch {
                commonUseCases.isAuthenticatedUseCase().collect {
                    _isAuthenticated.value = it
                }
            }
//            launch {
//                if (_isAuthenticated.value)
//                    updateUser()
//            }
        }
    }
}
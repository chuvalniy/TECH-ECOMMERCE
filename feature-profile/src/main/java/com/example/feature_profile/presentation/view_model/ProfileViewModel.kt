package com.example.feature_profile.presentation.view_model

import androidx.lifecycle.viewModelScope
import com.example.core.ui.BaseViewModel
import com.example.core.ui.UiText
import com.example.core.utils.Resource
import com.example.data_user_session.data.UserSession
import com.example.feature_profile.domain.repository.ProfileRepository
import com.example.feature_profile.presentation.model.ProfileEvent
import com.example.feature_profile.presentation.model.ProfileSideEffect
import com.example.feature_profile.presentation.model.ProfileState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository,
    private val userSession: UserSession
) : BaseViewModel<ProfileEvent, ProfileState, ProfileSideEffect>(ProfileState()) {

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            repository.fetchData(userSession.fetchUserId()).onEach { result ->
                when (result) {
                    is Resource.Error -> showSnackbar(
                        result.error
                            ?: UiText.StringResource(com.example.ui_component.R.string.unexpected_error)
                    )
                    is Resource.Loading -> _state.value =
                        _state.value.copy(isLoading = result.isLoading)
                    is Resource.Success -> result.data?.let { data ->
                        _state.value = _state.value.copy(data = data)
                    }
                }
            }.launchIn(this)
        }
    }

    override fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.BackButtonClicked -> backButtonClicked()
            ProfileEvent.CardsButtonClicked -> cardsButtonClicked()
            ProfileEvent.EditProfileButtonClicked -> editProfileButtonClicked()
            ProfileEvent.NotificationsButtonClicked -> notificationButtonClicked()
            ProfileEvent.OrderHistoryButtonClicked -> orderHistoryButtonClicked()
            ProfileEvent.ShoppingAddressButtonClicked -> shoppingAddressButtonClicked()
            ProfileEvent.StartOrderingButtonClicked -> startOrderingButtonClicked()
        }
    }

    private fun showSnackbar(message: UiText) = viewModelScope.launch {
        _sideEffect.send(ProfileSideEffect.ShowSnackbar(message))
    }

    private fun backButtonClicked() = viewModelScope.launch {
        _sideEffect.send(ProfileSideEffect.NavigateBack)
    }

    private fun cardsButtonClicked() = viewModelScope.launch {
        _sideEffect.send(ProfileSideEffect.NavigateToCards)
    }

    private fun editProfileButtonClicked() = viewModelScope.launch {
        _sideEffect.send(ProfileSideEffect.NavigateToEditProfile)
    }

    private fun notificationButtonClicked() = viewModelScope.launch {
        _sideEffect.send(ProfileSideEffect.NavigateToNotifications)
    }

    private fun orderHistoryButtonClicked() = viewModelScope.launch {
        _sideEffect.send(ProfileSideEffect.NavigateToOrderHistory)
    }

    private fun shoppingAddressButtonClicked() = viewModelScope.launch {
        _sideEffect.send(ProfileSideEffect.NavigateToShoppingAddress)
    }

    private fun startOrderingButtonClicked() = viewModelScope.launch {
        _sideEffect.send(ProfileSideEffect.NavigateToSearch)
    }
}
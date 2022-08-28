package com.example.feature_profile.presentation.profile.view_model

import androidx.lifecycle.viewModelScope
import com.example.core.extension.onEachResource
import com.example.core.ui.BaseViewModel
import com.example.core.ui.UiText
import com.example.data_user_session.data.UserPreferences
import com.example.feature_profile.domain.use_case.FetchProfile
import com.example.feature_profile.presentation.profile.model.ProfileEvent
import com.example.feature_profile.presentation.profile.model.ProfileSideEffect
import com.example.feature_profile.presentation.profile.model.ProfileState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val fetchProfile: FetchProfile,
    private val userPref: UserPreferences
) : BaseViewModel<ProfileEvent, ProfileState, ProfileSideEffect>(ProfileState()) {

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        fetchProfile.execute(userPref.fetchId()).onEachResource(
            onError = { showSnackbar(it) },
            onSuccess = { _state.value = _state.value.copy(data = it) },
            onLoading = { _state.value = _state.value.copy(isLoading = it) }
        ).launchIn(this)
    }

    override fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.BackButtonClicked -> backButtonClicked()
            ProfileEvent.CardsButtonClicked -> cardsButtonClicked()
            ProfileEvent.EditProfileButtonClicked -> editProfileButtonClicked()
            ProfileEvent.NotificationsButtonClicked -> notificationButtonClicked()
            ProfileEvent.OrderHistoryButtonClicked -> orderHistoryButtonClicked()
            ProfileEvent.ShoppingAddressButtonClicked -> shoppingAddressButtonClicked()
        }
    }

    private fun backButtonClicked() = viewModelScope.launch {
        _sideEffect.send(ProfileSideEffect.NavigateBack)
    }

    private fun cardsButtonClicked() = viewModelScope.launch {
        _sideEffect.send(ProfileSideEffect.NavigateToCards)
    }

    private fun editProfileButtonClicked() = viewModelScope.launch {
        _state.value.data?.let { _sideEffect.send(ProfileSideEffect.NavigateToEditProfile(it)) }
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

    private fun showSnackbar(message: UiText) = viewModelScope.launch {
        _sideEffect.send(ProfileSideEffect.ShowSnackbar(message))
    }
}
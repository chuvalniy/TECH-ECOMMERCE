package com.example.feature_profile.presentation.model

import com.example.core.ui.UiSideEffect
import com.example.core.ui.UiText

sealed class ProfileSideEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText) : ProfileSideEffect()
    object NavigateBack : ProfileSideEffect()
    object NavigateToEditProfile : ProfileSideEffect()
    object NavigateToShoppingAddress : ProfileSideEffect()
    object NavigateToOrderHistory : ProfileSideEffect()
    object NavigateToCards : ProfileSideEffect()
    object NavigateToNotifications : ProfileSideEffect()
    object NavigateToSearch : ProfileSideEffect()
}
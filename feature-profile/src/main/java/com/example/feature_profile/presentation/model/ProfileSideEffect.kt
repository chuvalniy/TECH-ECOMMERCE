package com.example.feature_profile.presentation.model

import com.example.core_ui_models.UiSideEffect
import com.example.core.ui.UiText

sealed class ProfileSideEffect : com.example.core_ui_models.UiSideEffect {
    data class ShowSnackbar(val message: UiText) : ProfileSideEffect()
    object NavigateBack : ProfileSideEffect()
    object NavigateToEditProfile : ProfileSideEffect()
    object NavigateToShoppingAddress : ProfileSideEffect()
    object NavigateToOrderHistory : ProfileSideEffect()
    object NavigateToCards : ProfileSideEffect()
    object NavigateToNotifications : ProfileSideEffect()
    object NavigateToSearch : ProfileSideEffect()
}
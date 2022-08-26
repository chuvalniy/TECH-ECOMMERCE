package com.example.feature_profile.presentation.profile.model

import com.example.core_ui_models.UiSideEffect
import com.example.core.ui.UiText
import com.example.feature_profile.domain.model.DomainDataSource

sealed class ProfileSideEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText) : ProfileSideEffect()
    data class NavigateToEditProfile(val data: DomainDataSource): ProfileSideEffect()
    object NavigateBack : ProfileSideEffect()
    object NavigateToShoppingAddress : ProfileSideEffect()
    object NavigateToOrderHistory : ProfileSideEffect()
    object NavigateToCards : ProfileSideEffect()
    object NavigateToNotifications : ProfileSideEffect()
}
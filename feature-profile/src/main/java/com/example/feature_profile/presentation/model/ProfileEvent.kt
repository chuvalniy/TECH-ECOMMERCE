package com.example.feature_profile.presentation.model

import com.example.core_ui_models.UiEvent

sealed class ProfileEvent : com.example.core_ui_models.UiEvent {
    object EditProfileButtonClicked : ProfileEvent()
    object ShoppingAddressButtonClicked : ProfileEvent()
    object OrderHistoryButtonClicked : ProfileEvent()
    object CardsButtonClicked : ProfileEvent()
    object NotificationsButtonClicked : ProfileEvent()
    object BackButtonClicked : ProfileEvent()
    object StartOrderingButtonClicked : ProfileEvent()
}
package com.example.feature_profile.presentation.edit_profile.model

import com.example.core.ui.UiText
import com.example.core_ui_models.UiSideEffect

sealed class EditProfileSideEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText) : EditProfileSideEffect()
    object NavigateBack : EditProfileSideEffect()
}
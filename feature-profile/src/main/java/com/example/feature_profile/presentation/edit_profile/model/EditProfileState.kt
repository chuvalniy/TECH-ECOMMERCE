package com.example.feature_profile.presentation.edit_profile.model

import com.example.core_ui_models.UiState

data class EditProfileState(
    val firstName: String = "",
    val secondName: String = "",
    val phoneNumber: String = "",
    val profileImage: String = "",
) : UiState
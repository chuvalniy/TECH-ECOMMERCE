package com.example.feature_profile.presentation.edit_profile.model

import com.example.core_ui_models.UiEvent

sealed class EditProfileEvent : UiEvent {
    data class FirstNameChanged(val firstName: String) : EditProfileEvent()
    data class SecondNameChanged(val secondName: String): EditProfileEvent()
    data class PhoneNumberChanged(val number: String): EditProfileEvent()
    data class ProfileImageChanged(val image: String): EditProfileEvent()
    object SubmitButtonClicked : EditProfileEvent()
    object BackButtonClicked : EditProfileEvent()
}
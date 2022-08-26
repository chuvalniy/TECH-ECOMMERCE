package com.example.feature_profile.presentation.edit_profile.view_model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.extension.onEachResource
import com.example.core.ui.BaseViewModel
import com.example.data_user_session.data.UserPreferences
import com.example.feature_profile.domain.model.DomainDataSource
import com.example.feature_profile.domain.use_case.UpdateProfile
import com.example.feature_profile.presentation.edit_profile.fragment.EditProfileFragment.Companion.USER_INFO
import com.example.feature_profile.presentation.edit_profile.model.EditProfileEvent
import com.example.feature_profile.presentation.edit_profile.model.EditProfileSideEffect
import com.example.feature_profile.presentation.edit_profile.model.EditProfileState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val savedState: SavedStateHandle,
    private val updateProfile: UpdateProfile,
    private val userPref: UserPreferences,
) : BaseViewModel<EditProfileEvent, EditProfileState, EditProfileSideEffect>(EditProfileState()) {

    private val userInfo = savedState.get<DomainDataSource>(USER_INFO)!!

    init {
        _state.value = _state.value.copy(
            firstName = userInfo.firstName,
            secondName = userInfo.lastName,
            phoneNumber = userInfo.phoneNumber,
            profileImage = userInfo.image
        )
    }

    override fun onEvent(event: EditProfileEvent) {
        when (event) {
            is EditProfileEvent.FirstNameChanged -> _state.value =
                _state.value.copy(firstName = event.firstName)
            is EditProfileEvent.PhoneNumberChanged -> _state.value =
                _state.value.copy(secondName = event.number)
            is EditProfileEvent.ProfileImageChanged -> _state.value =
                _state.value.copy(profileImage = event.image)
            is EditProfileEvent.SecondNameChanged -> _state.value =
                _state.value.copy(secondName = event.secondName)
            is EditProfileEvent.BackButtonClicked -> backButtonClicked()
            is EditProfileEvent.SubmitButtonClicked -> submitButtonClicked()
        }
    }

    private fun backButtonClicked() = viewModelScope.launch {
        _sideEffect.send(EditProfileSideEffect.NavigateBack)
    }

    private fun submitButtonClicked() = viewModelScope.launch {
        val updatedUserInfo = userInfo.copy(
            firstName = _state.value.firstName,
            lastName = _state.value.secondName,
            phoneNumber = _state.value.phoneNumber,
            image = _state.value.profileImage
        )

        updateProfile.execute(userPref.fetchId(), updatedUserInfo).onEachResource(
            onError = { Log.d("TAGTAG", it.toString() )},
            onSuccess = { Log.d("TAGTAG", "success")}
        ).launchIn(this)
    }
}
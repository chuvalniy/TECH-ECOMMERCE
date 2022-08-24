package com.example.core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<E : com.example.core_ui_models.UiEvent, S: com.example.core_ui_models.UiState, SE: com.example.core_ui_models.UiSideEffect>(
    initialState: S
) : ViewModel(), com.example.core_ui_models.EventHandler<E> {

    protected val _state = MutableStateFlow(initialState)
    val state get() = _state.asStateFlow()

    protected val _sideEffect = Channel<SE>()
    val sideEffect get() = _sideEffect.receiveAsFlow()
}
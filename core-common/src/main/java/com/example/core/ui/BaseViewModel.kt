package com.example.core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<E : UiEvent, S: UiState, SE: UiSideEffect>(
    initialState: S
) : ViewModel(), EventHandler<E> {

    protected val _state = MutableStateFlow(initialState)
    val state get() = _state.asStateFlow()

    protected val _sideEffect = Channel<SE>()
    val sideEffect get() = _sideEffect.receiveAsFlow()
}
package com.example.core_ui_models

interface EventHandler<E : UiEvent> {

    fun onEvent(event: E)
}
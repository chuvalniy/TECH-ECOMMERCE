package com.example.core.ui

interface EventHandler<E : UiEvent> {

    fun onEvent(event: E)
}
package com.example.core.ui

interface EventHandler<E> {

    fun onEvent(event: E)
}
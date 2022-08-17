package com.example.core.utils

import com.example.core.ui.UiText

sealed class Resource<T>(val data: T? = null, val error: UiText? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(data: T? = null, error: UiText?): Resource<T>(data, error)
    class Loading<T>(val isLoading: Boolean = true): Resource<T>(null)
}
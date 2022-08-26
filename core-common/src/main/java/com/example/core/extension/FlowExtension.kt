package com.example.core.extension

import com.example.core.ui.UiText
import com.example.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

inline fun <T> Flow<Resource<T>>.onEachResource(
    crossinline onError: (UiText) -> Unit,
    crossinline onSuccess: (T) -> Unit,
    crossinline onLoading: (Boolean) -> Unit = { },
) = onEach { result ->
    when (result) {
        is Resource.Error -> onError(
            result.error
                ?: UiText.StringResource(com.example.ui_component.R.string.unexpected_error)
        )
        is Resource.Loading -> onLoading(result.isLoading)
        is Resource.Success -> result.data?.let(onSuccess)
    }
}

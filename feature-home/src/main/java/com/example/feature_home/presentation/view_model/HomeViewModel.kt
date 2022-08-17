package com.example.feature_home.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.ui.EventHandler
import com.example.core.ui.UiText
import com.example.core.utils.Resource
import com.example.feature_home.domain.use_case.FetchData
import com.example.feature_home.presentation.model.UiEvent
import com.example.feature_home.presentation.model.UiSideEffect
import com.example.feature_home.presentation.model.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchData: FetchData
) : ViewModel(), EventHandler<UiEvent> {

    private val _uiState = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

    private val _uiEffect = Channel<UiSideEffect>()
    val uiEffect get() = _uiEffect.receiveAsFlow()

    init {
        fetchData()
    }

    private fun fetchData(
        category: String = _uiState.value.category
    ) {
        viewModelScope.launch {
            fetchData.execute().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        showSnackbar(result.error ?: UiText.DynamicString("error")) // todo
                    }
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = result.isLoading)
                    }
                    is Resource.Success -> {
                        result.data?.let { _uiState.value = _uiState.value.copy(data = it) }
                    }
                }
            }.launchIn(this)
        }
    }

    override fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.CategorySelected -> categorySelected(category = event.category)
            is UiEvent.ProductClicked -> TODO()
            is UiEvent.SearchClicked -> TODO()
        }
    }

    private fun categorySelected(category: String) {
        if (category == _uiState.value.category) return

        _uiState.value = _uiState.value.copy(category = category)
        fetchData()
    }

    private fun showSnackbar(message: UiText) = viewModelScope.launch {
        _uiEffect.send(UiSideEffect.ShowSnackbar(message))
    }
}
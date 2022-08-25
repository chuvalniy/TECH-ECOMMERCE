package com.example.feature_home.presentation.view_model

import androidx.lifecycle.viewModelScope
import com.example.core.ui.BaseViewModel
import com.example.core.ui.UiText
import com.example.core.utils.Resource
import com.example.feature_home.domain.model.DomainDataSource
import com.example.feature_home.domain.repository.HomeRepository
import com.example.feature_home.presentation.model.HomeEvent
import com.example.feature_home.presentation.model.HomeSideEffect
import com.example.feature_home.presentation.model.HomeState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository,
) : BaseViewModel<HomeEvent, HomeState, HomeSideEffect>(HomeState()) {

    init {
        fetchData()
    }

    private fun fetchData(
        category: String = _state.value.category
    ) {
        viewModelScope.launch {
            repository.fetchData(category).onEach { result ->
                when (result) {
                    is Resource.Error -> showSnackbar(
                        result.error
                            ?: UiText.StringResource(com.example.ui_component.R.string.unexpected_error)
                    )
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = result.isLoading)
                    }
                    is Resource.Success ->
                        result.data?.let { _state.value = _state.value.copy(data = it) }
                }
            }.launchIn(this)
        }
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.CategorySelected -> categorySelected(category = event.category)
            is HomeEvent.ProductClicked -> productClicked(event.id)
            is HomeEvent.SearchClicked -> searchClicked()
        }
    }

    private fun productClicked(id: String) = viewModelScope.launch {
        _sideEffect.send(HomeSideEffect.NavigateToDetails(id))
    }

    private fun searchClicked() = viewModelScope.launch {
        _sideEffect.send(HomeSideEffect.NavigateToSearch)
    }

    private fun categorySelected(category: String) {
        if (category == _state.value.category) return

        _state.value = _state.value.copy(category = category)
        fetchData()
    }

    private fun showSnackbar(message: UiText) = viewModelScope.launch {
        _sideEffect.send(HomeSideEffect.ShowSnackbar(message))
    }
}
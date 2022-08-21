package com.example.feature_details.presentation.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.ui.BaseViewModel
import com.example.core.utils.Resource
import com.example.feature_details.domain.model.DomainDataSource
import com.example.feature_details.domain.repository.DetailsRepository
import com.example.feature_details.presentation.model.DetailsEvent
import com.example.feature_details.presentation.model.DetailsSideEffect
import com.example.feature_details.presentation.model.DetailsState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: DetailsRepository,
    private val savedState: SavedStateHandle
) : BaseViewModel<DetailsEvent, DetailsState, DetailsSideEffect>(DetailsState()) {

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            repository.fetchData(id = savedState.get<String>("id") ?: "").onEach { result ->
                when (result) {
                    is Resource.Error -> TODO()
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = result.isLoading)
                    }
                    is Resource.Success -> processSuccessState(result)
                }
            }.launchIn(this)
        }
    }

    private fun processSuccessState(result: Resource<DomainDataSource>) {
        result.data?.let { data -> _state.value = _state.value.copy(data = data) }
    }

    override fun onEvent(event: DetailsEvent) {
        when (event) {
            DetailsEvent.AddToCartButtonClicked -> TODO()
            DetailsEvent.AddToFavoriteButtonClicked -> TODO()
            DetailsEvent.BackButtonClicked -> backButtonClicked()
        }
    }

    private fun backButtonClicked() = viewModelScope.launch {
        _sideEffect.send(DetailsSideEffect.NavigateBack)
    }
}
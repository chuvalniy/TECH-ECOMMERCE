package com.example.feature_search.presentation.view_model

import androidx.lifecycle.viewModelScope
import com.example.core.ui.BaseViewModel
import com.example.core.ui.UiText
import com.example.core.utils.Resource
import com.example.feature_search.domain.model.DomainDataSource
import com.example.feature_search.domain.repository.SearchRepository
import com.example.feature_search.presentation.model.SearchEvent
import com.example.feature_search.presentation.model.SearchSideEffect
import com.example.feature_search.presentation.model.SearchState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: SearchRepository
) : BaseViewModel<SearchEvent, SearchState, SearchSideEffect>(SearchState()) {

    init {
        fetchData()
    }

    private fun fetchData(
        searchQuery: String = _state.value.searchQuery
    ) {
        viewModelScope.launch {
            repository.fetchCache(searchQuery).onEach { result ->
                when (result) {
                    is Resource.Error -> processErrorState(result)
                    is Resource.Loading -> _state.value =
                        _state.value.copy(isLoading = result.isLoading)
                    is Resource.Success -> processSuccessState(result)
                }
            }.launchIn(this)
        }
    }

    private fun processErrorState(result: Resource<List<DomainDataSource>>) {
        viewModelScope.launch {
            _sideEffect.send(
                SearchSideEffect.ShowSnackbar(
                    result.error ?: UiText.StringResource(
                        com.example.ui_component.R.string.unexpected_error
                    )
                )
            )
        }
    }

    private fun processSuccessState(result: Resource<List<DomainDataSource>>) {
        result.data?.let { data ->
            _state.value = _state.value.copy(data = data)
        }
    }

    override fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.BackButtonClicked -> backButtonClicked()
            is SearchEvent.ProductClicked -> productClicked(event.id)
            is SearchEvent.QueryChanged -> queryChanged(event.searchQuery)
        }
    }

    private var searchJob: Job? = null

    private fun queryChanged(searchQuery: String) {
        if (searchQuery == _state.value.searchQuery) return

        _state.value = _state.value.copy(searchQuery = searchQuery)

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            fetchData()
        }
    }

    private fun backButtonClicked() = viewModelScope.launch {
        _sideEffect.send(SearchSideEffect.NavigateBack)
    }

    private fun productClicked(id: String) = viewModelScope.launch {
        _sideEffect.send(SearchSideEffect.NavigateToDetails(id))
    }
}
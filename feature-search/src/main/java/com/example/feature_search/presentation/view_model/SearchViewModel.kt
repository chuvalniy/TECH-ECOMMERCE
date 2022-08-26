package com.example.feature_search.presentation.view_model

import androidx.lifecycle.viewModelScope
import com.example.core.extension.onEachResource
import com.example.core.ui.BaseViewModel
import com.example.core.ui.UiText
import com.example.feature_search.domain.repository.SearchRepository
import com.example.feature_search.domain.use_case.SearchData
import com.example.feature_search.presentation.model.SearchEvent
import com.example.feature_search.presentation.model.SearchSideEffect
import com.example.feature_search.presentation.model.SearchState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchData: SearchData
) : BaseViewModel<SearchEvent, SearchState, SearchSideEffect>(SearchState()) {

    init {
        fetchData()
    }

    private fun fetchData(query: String = _state.value.searchQuery) = viewModelScope.launch {
        searchData.execute(query).onEachResource(
            onError = { showSnackbar(it) },
            onSuccess = { _state.value = _state.value.copy(data = it) },
            onLoading = { _state.value = _state.value.copy(isLoading = it) }
        ).launchIn(this)
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

    private fun showSnackbar(message: UiText) = viewModelScope.launch {
        _sideEffect.send(SearchSideEffect.ShowSnackbar(message))
    }
}
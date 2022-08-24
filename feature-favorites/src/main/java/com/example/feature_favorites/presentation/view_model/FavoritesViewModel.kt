package com.example.feature_favorites.presentation.view_model

import androidx.lifecycle.viewModelScope
import com.example.core.ui.BaseViewModel
import com.example.core.ui.UiText
import com.example.core.utils.Resource
import com.example.data_user_session.data.UserSession
import com.example.feature_favorites.domain.model.DomainDataSource
import com.example.feature_favorites.domain.repository.FavoritesRepository
import com.example.feature_favorites.presentation.model.FavoritesEvent
import com.example.feature_favorites.presentation.model.FavoritesSideEffect
import com.example.feature_favorites.presentation.model.FavoritesState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: FavoritesRepository,
    private val userSession: UserSession
) : BaseViewModel<FavoritesEvent, FavoritesState, FavoritesSideEffect>(FavoritesState()) {

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            repository.fetchData(userId = userSession.fetchUserId()).onEach { result ->
                when (result) {
                    is Resource.Error -> showSnackbar(
                        result.error
                            ?: UiText.StringResource(com.example.ui_component.R.string.unexpected_error)
                    )
                    is Resource.Loading -> _state.value =
                        _state.value.copy(isLoading = result.isLoading)
                    is Resource.Success -> result.data?.let { data ->
                        _state.value = _state.value.copy(data = data)
                    }
                }
            }.launchIn(this)
        }
    }

    private fun showSnackbar(message: UiText) = viewModelScope.launch {
        _sideEffect.send(FavoritesSideEffect.ShowSnackbar(message))
    }

    override fun onEvent(event: FavoritesEvent) {
        when (event) {
            is FavoritesEvent.BackButtonClicked -> backButtonClicked()
            is FavoritesEvent.ItemSwiped -> favoritesSwiped(event.item)
            is FavoritesEvent.UndoClicked -> undoClicked(event.item)
        }
    }

    private fun backButtonClicked() = viewModelScope.launch {
        _sideEffect.send(FavoritesSideEffect.NavigateBack)
    }

    private fun favoritesSwiped(item: DomainDataSource) = viewModelScope.launch {
        repository.deleteData(
            userId = userSession.fetchUserId(),
            data = item
        )
        _sideEffect.send(FavoritesSideEffect.ShowUndoSnackbar(item))
        fetchData()
    }

    private fun undoClicked(item: DomainDataSource) = viewModelScope.launch {
        repository.insertData(
            userId = userSession.fetchUserId(),
            data = item
        )
        fetchData()
    }
}
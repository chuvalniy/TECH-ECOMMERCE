package com.example.feature_favorites.presentation.view_model

import androidx.lifecycle.viewModelScope
import com.example.core.extension.onEachResource
import com.example.core.ui.BaseViewModel
import com.example.core.ui.UiText
import com.example.data_user_session.data.UserPreferences
import com.example.feature_favorites.domain.model.DomainDataSource
import com.example.feature_favorites.domain.use_case.DeleteFavorite
import com.example.feature_favorites.domain.use_case.FetchFavorites
import com.example.feature_favorites.domain.use_case.InsertFavorite
import com.example.feature_favorites.presentation.model.FavoritesEvent
import com.example.feature_favorites.presentation.model.FavoritesSideEffect
import com.example.feature_favorites.presentation.model.FavoritesState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val deleteFavorite: DeleteFavorite,
    private val insertFavorite: InsertFavorite,
    private val fetchFavorites: FetchFavorites,
    private val userPref: UserPreferences
) : BaseViewModel<FavoritesEvent, FavoritesState, FavoritesSideEffect>(FavoritesState()) {

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        fetchFavorites.execute(userPref.fetchId()).onEachResource(
            onError = { showSnackbar(it) },
            onSuccess = { _state.value = _state.value.copy(data = it) },
            onLoading = { _state.value = _state.value.copy(isLoading = it) }
        ).launchIn(this)
    }

    override fun onEvent(event: FavoritesEvent) {
        when (event) {
            is FavoritesEvent.BackButtonClicked -> backButtonClicked()
            is FavoritesEvent.ItemSwiped -> itemSwiped(event.item)
            is FavoritesEvent.UndoClicked -> undoClicked(event.item)
        }
    }

    private fun backButtonClicked() = viewModelScope.launch {
        _sideEffect.send(FavoritesSideEffect.NavigateBack)
    }

    private fun itemSwiped(data: DomainDataSource) = viewModelScope.launch {
        deleteFavorite.execute(userId = userPref.fetchId(), data = data).onEachResource(
            onError = { showSnackbar(it) },
            onSuccess = {
                showUndoSnackbar(it, data)
                fetchData()
            }
        ).launchIn(this)
    }

    private fun undoClicked(item: DomainDataSource) = viewModelScope.launch {
        insertFavorite.execute(userId = userPref.fetchId(), data = item).onEachResource(
            onError = { showSnackbar(it) },
            onSuccess = { fetchData() }
        )
    }

    private fun showUndoSnackbar(message: UiText, data: DomainDataSource) = viewModelScope.launch {
        _sideEffect.send(FavoritesSideEffect.ShowUndoSnackbar(message, data))
    }

    private fun showSnackbar(message: UiText) = viewModelScope.launch {
        _sideEffect.send(FavoritesSideEffect.ShowSnackbar(message))
    }
}
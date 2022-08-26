package com.example.feature_details.presentation.view_model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.extension.onEachResource
import com.example.core.ui.BaseViewModel
import com.example.core.ui.UiText
import com.example.data_user_session.data.UserSession
import com.example.feature_cart.domain.repository.CartRepository
import com.example.feature_details.domain.use_case.FetchData
import com.example.feature_details.presentation.model.DetailsEvent
import com.example.feature_details.presentation.model.DetailsSideEffect
import com.example.feature_details.presentation.model.DetailsState
import com.example.feature_favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class DetailsViewModel(
    // TODO
    private val cartRepository: CartRepository,
    private val favoritesRepository: FavoritesRepository,
    private val userSession: UserSession,
    private val fetchData: FetchData,
    private val savedState: SavedStateHandle,
) : BaseViewModel<DetailsEvent, DetailsState, DetailsSideEffect>(DetailsState()) {

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {

        fetchData.execute(
            userId = userSession.fetchUserId(),
            id = savedState.get<String>("id") ?: ""
        ).onEachResource(
            onError = { showSnackbar(it) },
            onSuccess = {
                _state.value = _state.value.copy(model = it)
                Log.d("TAGTAG", _state.value.model.data.id)
            },
        ).launchIn(this)
    }

    override fun onEvent(event: DetailsEvent) {
        when (event) {
            DetailsEvent.AddToCartButtonClicked -> addToCartButtonClicked()
            DetailsEvent.AddToFavoriteButtonClicked -> favoriteButtonClicked()
            DetailsEvent.BackButtonClicked -> backButtonClicked()
        }
    }

    private fun addToCartButtonClicked() = viewModelScope.launch {
        cartRepository.insertData(
            userId = userSession.fetchUserId(),
            data = com.example.feature_cart.domain.model.DomainDataSource(
                id = _state.value.model.data.id,
                img = _state.value.model.data.images.first(),
                model = _state.value.model.data.modelFull,
                price = _state.value.model.data.price,
            )
        ).onEachResource(
            onError = { showSnackbar(it) },
            onSuccess = { showSnackbar(it) },
        ).launchIn(this)
    }

    private fun favoriteButtonClicked() = viewModelScope.launch {

        val data = com.example.feature_favorites.domain.model.DomainDataSource(
            id = _state.value.model.data.id,
            img = _state.value.model.data.images.first(),
            model = _state.value.model.data.modelFull,
            price = _state.value.model.data.price,
        )

        if (!_state.value.model.isFavorites) {
            favoritesRepository.insertData(
                userId = userSession.fetchUserId(),
                data = data
            ).onEachResource(
                onError = { showSnackbar(it) },
                onSuccess = {
                    showSnackbar(it)
                    fetchData()
                }
            ).launchIn(this)
        } else if (_state.value.model.isFavorites) {
            favoritesRepository.deleteData(
                userId = userSession.fetchUserId(),
                data = data
            ).onEachResource(
                onError = { showSnackbar(it) },
                onSuccess = {
                    showSnackbar(it)
                    fetchData()
                }
            ).launchIn(this)
        }
    }

    private fun backButtonClicked() = viewModelScope.launch {
        _sideEffect.send(DetailsSideEffect.NavigateBack)
    }

    private fun showSnackbar(message: UiText) = viewModelScope.launch {
        _sideEffect.send(DetailsSideEffect.ShowSnackbar(message))
    }
}
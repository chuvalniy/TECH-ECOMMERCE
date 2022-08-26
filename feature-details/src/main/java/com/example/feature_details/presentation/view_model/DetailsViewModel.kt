package com.example.feature_details.presentation.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.extension.onEachResource
import com.example.core.ui.BaseViewModel
import com.example.core.ui.UiText
import com.example.data_user_session.data.UserPreferences
import com.example.feature_cart.domain.use_case.InsertCart
import com.example.feature_details.domain.use_case.FetchDetails
import com.example.feature_details.presentation.model.DetailsEvent
import com.example.feature_details.presentation.model.DetailsSideEffect
import com.example.feature_details.presentation.model.DetailsState
import com.example.feature_favorites.domain.use_case.DeleteFavorite
import com.example.feature_favorites.domain.use_case.InsertFavorite
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val insertFavorite: InsertFavorite,
    private val deleteFavorite: DeleteFavorite,
    private val fetchDetails: FetchDetails,
    private val insertCart: InsertCart,
    private val userPref: UserPreferences,
    private val savedState: SavedStateHandle,
) : BaseViewModel<DetailsEvent, DetailsState, DetailsSideEffect>(DetailsState()) {

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        fetchDetails.execute(
            userId = userPref.fetchId(),
            id = savedState.get<String>("id") ?: ""
        ).onEachResource(
            onError = { showSnackbar(it) },
            onSuccess = {
                _state.value = _state.value.copy(model = it)
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
        insertCart.execute(
            userId = userPref.fetchId(),
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

        when (_state.value.model.isFavorites) {
            true -> {
                deleteFavorite.execute(userId = userPref.fetchId(), data = data).onEachResource(
                    onError = { showSnackbar(it) },
                    onSuccess = {
                        showSnackbar(it)
                        fetchData()
                    }
                ).launchIn(this)
            }
            false -> {
                insertFavorite.execute(userId = userPref.fetchId(), data = data).onEachResource(
                    onError = { showSnackbar(it) },
                    onSuccess = {
                        showSnackbar(it)
                        fetchData()
                    }
                ).launchIn(this)
            }
        }
    }

    private fun backButtonClicked() = viewModelScope.launch {
        _sideEffect.send(DetailsSideEffect.NavigateBack)
    }

    private fun showSnackbar(message: UiText) = viewModelScope.launch {
        _sideEffect.send(DetailsSideEffect.ShowSnackbar(message))
    }
}
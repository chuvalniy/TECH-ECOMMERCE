package com.example.feature_details.presentation.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.ui.BaseViewModel
import com.example.core.ui.UiText
import com.example.core.utils.Resource
import com.example.data_user_session.data.UserSession
import com.example.feature_cart.domain.repository.CartRepository
import com.example.feature_details.domain.repository.DetailsRepository
import com.example.feature_details.presentation.model.DetailsEvent
import com.example.feature_details.presentation.model.DetailsSideEffect
import com.example.feature_details.presentation.model.DetailsState
import com.example.feature_favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val detailsRepository: DetailsRepository,
    private val cartRepository: CartRepository,
    private val favoritesRepository: FavoritesRepository,
    private val userSession: UserSession,
    private val savedState: SavedStateHandle,
) : BaseViewModel<DetailsEvent, DetailsState, DetailsSideEffect>(DetailsState()) {

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            detailsRepository.fetchData(id = savedState.get<String>("id") ?: "").onEach { result ->
                when (result) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = result.isLoading)
                    }
                    is Resource.Success -> result.data?.let { data ->
                        _state.value = _state.value.copy(data = data)
                    }
                }
            }.launchIn(this)
        }
    }

    override fun onEvent(event: DetailsEvent) {
        when (event) {
            DetailsEvent.AddToCartButtonClicked -> addToCartButtonClicked()
            DetailsEvent.AddToFavoriteButtonClicked -> addToFavoriteButtonClicked()
            DetailsEvent.BackButtonClicked -> backButtonClicked()
        }
    }

    private fun addToCartButtonClicked() = viewModelScope.launch {
        cartRepository.insertData(
            userId = userSession.fetchUserId(),
            data = com.example.feature_cart.domain.model.DomainDataSource(
                id = _state.value.data.id,
                img = _state.value.data.images.first(),
                model = _state.value.data.modelFull,
                price = _state.value.data.price,
            )
        ).onEach { result ->
            when (result) {
                is Resource.Error -> showSnackbar(
                    result.error
                        ?: UiText.StringResource(com.example.ui_component.R.string.unexpected_error)
                )
                is Resource.Success -> result.data?.let { message ->
                    showSnackbar(message)
                }
                else -> Unit
            }
        }.launchIn(this)
    }

    private fun addToFavoriteButtonClicked() = viewModelScope.launch {
        favoritesRepository.insertData(
            userId = userSession.fetchUserId(),
            data = com.example.feature_favorites.domain.model.DomainDataSource(
                id = _state.value.data.id,
                img = _state.value.data.images.first(),
                model = _state.value.data.modelFull,
                price = _state.value.data.price,
            )
        ).onEach { result ->
            when (result) {
                is Resource.Error -> showSnackbar(
                    result.error
                        ?: UiText.StringResource(com.example.ui_component.R.string.unexpected_error)
                )
                is Resource.Success -> result.data?.let { message ->
                    showSnackbar(message = message)
                }
                else -> Unit
            }
        }.launchIn(this)
    }

    private fun backButtonClicked() = viewModelScope.launch {
        _sideEffect.send(DetailsSideEffect.NavigateBack)
    }

    private fun showSnackbar(message: UiText) = viewModelScope.launch {
        _sideEffect.send(DetailsSideEffect.ShowSnackbar(message))
    }
}
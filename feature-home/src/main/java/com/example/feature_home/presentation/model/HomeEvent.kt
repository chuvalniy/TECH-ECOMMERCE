package com.example.feature_home.presentation.model

import com.example.core_ui_models.UiEvent

sealed class HomeEvent : UiEvent {
    data class ProductClicked(val id: String) : HomeEvent()
    data class CategorySelected(val category: String) : HomeEvent()
    object SearchClicked : HomeEvent()
}
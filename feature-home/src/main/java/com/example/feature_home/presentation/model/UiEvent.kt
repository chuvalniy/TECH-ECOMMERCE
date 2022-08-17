package com.example.feature_home.presentation.model

sealed class UiEvent {
    data class ProductClicked(val id: String): UiEvent()
    data class CategorySelected(val category: String): UiEvent()
    object SearchClicked : UiEvent()
}
package com.example.feature_search.presentation.model

import com.example.core_ui_models.UiEvent

sealed class SearchEvent : UiEvent {
    data class QueryChanged(val searchQuery: String): SearchEvent()
    data class ProductClicked(val id: String): SearchEvent()
    object BackButtonClicked : SearchEvent()
}
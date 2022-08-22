package com.example.feature_login.domain.use_case

import com.example.core.ui.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)
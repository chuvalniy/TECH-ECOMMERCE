package com.example.feature_login.domain.use_case

import com.example.core.ui.UiText
import com.example.feature_login.R

class ValidatePassword {

    fun execute(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.empty_password_field)
            )
        }

        return ValidationResult(successful = true)
    }
}
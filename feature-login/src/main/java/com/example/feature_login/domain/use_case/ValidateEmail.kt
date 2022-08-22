package com.example.feature_login.domain.use_case

import com.example.core.ui.UiText
import com.example.feature_login.R

class ValidateEmail {

    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.empty_email_field)
            )
        }

        return ValidationResult(successful = true)
    }
}
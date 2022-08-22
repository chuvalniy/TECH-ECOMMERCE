package com.example.feature_login.domain.use_case

import com.example.core.ui.UiText
import com.example.feature_login.R

class ValidateRepeatedPassword {

    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if (repeatedPassword != password) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.repeated_password_error)
            )
        }

        return ValidationResult(successful = true)
    }
}
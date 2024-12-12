package ru.bashcony.evotortest.presentation.registration

import androidx.core.text.isDigitsOnly

object RegistrationValidator {
    fun isNumberValid(value: String) = value.isDigitsOnly() && value.length == 16
    fun isOtherFieldsValid(value: String) = value.isNotBlank()
}
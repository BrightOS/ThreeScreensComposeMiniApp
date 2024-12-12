package ru.bashcony.evotortest.presentation.registration

import androidx.compose.runtime.Stable
import ru.bashcony.evotortest.common.ViewModelState

@Stable
sealed class RegistrationState : ViewModelState {
    data object Initial : RegistrationState()
    data object ChangesSaved : RegistrationState()
    data object Error : RegistrationState()
}
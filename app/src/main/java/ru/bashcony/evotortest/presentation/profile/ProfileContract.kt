package ru.bashcony.evotortest.presentation.profile

import androidx.compose.runtime.Stable
import ru.bashcony.evotortest.common.ViewModelState
import ru.bashcony.evotortest.domain.users.entity.UserEntity

@Stable
sealed class ProfileState : ViewModelState {
    data class ProfileLoaded(val user: UserEntity) : ProfileState()
    data class ShowToast(val text: String) : ProfileState()
    data object Error : ProfileState()
    data object Loading : ProfileState()
}
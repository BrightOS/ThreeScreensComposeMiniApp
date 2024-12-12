package ru.bashcony.evotortest.presentation.registration

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.bashcony.evotortest.common.StateViewModel
import ru.bashcony.evotortest.domain.users.entity.UserEntity
import ru.bashcony.evotortest.domain.users.usecase.UpdateCurrentUserUseCase
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val updateCurrentUserUseCase: UpdateCurrentUserUseCase
) : StateViewModel<RegistrationState>() {

    override fun setInitialState(): RegistrationState = RegistrationState.Initial

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        setState { RegistrationState.Error }
    }

    fun updateCurrentUser(newUser: UserEntity) {
        viewModelScope.launch(exceptionHandler) {
            updateCurrentUserUseCase(newUser)
        }
        setState { RegistrationState.ChangesSaved }
    }
}
package ru.bashcony.evotortest.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.bashcony.evotortest.common.Result
import ru.bashcony.evotortest.common.StateViewModel
import ru.bashcony.evotortest.common.asResult
import ru.bashcony.evotortest.domain.users.entity.UserEntity
import ru.bashcony.evotortest.domain.users.usecase.GetCurrentUserUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : StateViewModel<ProfileState>() {

    override fun setInitialState(): ProfileState = ProfileState.Loading

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            getCurrentUserUseCase()
                .asResult()
                .collect { result ->
                    when (result) {
                        Result.Loading -> setState { ProfileState.Loading }
                        is Result.Error -> setState { ProfileState.Error }
                        is Result.Success -> setState { ProfileState.ProfileLoaded(result.data) }
                    }
                }
        }
    }

}
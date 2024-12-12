package ru.bashcony.evotortest.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface ViewModelState

abstract class StateViewModel<UiState : ViewModelState> : ViewModel() {

    abstract fun setInitialState(): UiState

    private val initialState: UiState by lazy { setInitialState() }

    private val _viewState: MutableStateFlow<UiState> = MutableStateFlow(initialState)
    open val viewState: StateFlow<UiState> = _viewState.asStateFlow()

    protected fun setState(reducer: UiState.() -> UiState) {
        val newState = viewState.value.reducer()
        _viewState.update { newState }
    }
}
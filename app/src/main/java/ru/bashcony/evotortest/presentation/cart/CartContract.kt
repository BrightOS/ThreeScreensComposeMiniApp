package ru.bashcony.evotortest.presentation.cart

import ru.bashcony.evotortest.common.ViewModelState
import ru.bashcony.evotortest.domain.cart.entity.DayCartEntity

sealed class CartState : ViewModelState {
    data object Loading : CartState()
    data object Error : CartState()
    data class CartLoaded(val cart: List<DayCartEntity>) : CartState()
}
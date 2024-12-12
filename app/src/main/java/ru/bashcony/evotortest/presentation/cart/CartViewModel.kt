package ru.bashcony.evotortest.presentation.cart

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.bashcony.evotortest.common.StateViewModel
import ru.bashcony.evotortest.domain.cart.usecase.GetCartUseCase
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase,
) : StateViewModel<CartState>() {

    override fun setInitialState() = CartState.Loading

    init {
        setState {
            CartState.CartLoaded(getCartUseCase())
        }
    }
}
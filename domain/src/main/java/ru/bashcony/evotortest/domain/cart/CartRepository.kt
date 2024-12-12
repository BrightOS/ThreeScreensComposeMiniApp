package ru.bashcony.evotortest.domain.cart

import ru.bashcony.evotortest.domain.cart.entity.DayCartEntity

interface CartRepository {
    fun getCart(): List<DayCartEntity>
}
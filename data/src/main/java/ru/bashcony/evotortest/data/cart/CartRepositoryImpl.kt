package ru.bashcony.evotortest.data.cart

import ru.bashcony.evotortest.data.cart.db.CartStorage
import ru.bashcony.evotortest.data.cart.model.DayCartModel
import ru.bashcony.evotortest.domain.cart.CartRepository
import ru.bashcony.evotortest.domain.cart.entity.DayCartEntity

class CartRepositoryImpl : CartRepository {
    override fun getCart(): List<DayCartEntity> {
        return CartStorage.getCart().data?.map { it.toEntity() }.orEmpty()
    }

    companion object {
        fun DayCartModel.toEntity() =
            DayCartEntity(
                date = date.orEmpty(),
                name = name.orEmpty(),
            )
    }
}
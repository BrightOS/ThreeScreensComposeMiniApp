package ru.bashcony.evotortest.data.cart.db

import com.google.gson.Gson
import ru.bashcony.evotortest.data.cart.model.CartModel

object CartStorage {

    private val cartJson =
        "{\"data\":[{\"date\":\"2022-09-10T21:55:33Z\",\"name\":[\"123\",\"321\"]},{\"date\":\"2022-09-10T21:50:33Z\",\"name\":[\"1234\",\"4321\"]},{\"date\":\"2022-09-08T01:55:33Z\",\"name\":[\"12345\",\"54321\"]},{\"date\":\"2022-09-07T21:55:33Z\",\"name\":[\"123456\",\"654321\"]},{\"date\":\"2022-09-07T11:55:33Z\",\"name\":[\"1234567\",\"7654321\"]}]}"

    fun getCart(): CartModel {
        return Gson().fromJson(cartJson, CartModel::class.java)
    }
}
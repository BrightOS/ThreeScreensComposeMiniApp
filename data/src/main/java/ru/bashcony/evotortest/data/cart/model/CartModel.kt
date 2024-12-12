package ru.bashcony.evotortest.data.cart.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CartModel(
    @SerializedName("data") val data: List<DayCartModel>?
)
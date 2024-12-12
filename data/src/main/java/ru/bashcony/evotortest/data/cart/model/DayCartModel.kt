package ru.bashcony.evotortest.data.cart.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DayCartModel(
    @SerializedName("date") val date: String?,
    @SerializedName("name") val name: List<String>?,
)
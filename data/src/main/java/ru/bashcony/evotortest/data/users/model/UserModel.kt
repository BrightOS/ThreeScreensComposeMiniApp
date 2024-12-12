package ru.bashcony.evotortest.data.users.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserModel(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("member_number") val memberNumber: String,
    @SerializedName("bank_code") val bankCode: String,
)
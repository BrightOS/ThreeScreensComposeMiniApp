package ru.bashcony.evotortest.data.users

import android.content.SharedPreferences
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.bashcony.evotortest.data.common.utils.delegates
import ru.bashcony.evotortest.data.users.model.UserModel
import ru.bashcony.evotortest.domain.users.UsersRepository
import ru.bashcony.evotortest.domain.users.entity.UserEntity

class UsersRepositoryImpl(prefs: SharedPreferences) : UsersRepository {
    private var currentUserPreference: String by prefs.delegates.string(
        key = PREFERENCE_CURRENT_USER,
        default = Gson().toJson(
            UserModel(
                firstName = "Имя",
                lastName = "Фамилия",
                phoneNumber = "89999999999",
                memberNumber = "123412341234",
                bankCode = "123442"
            )
        ),
        onSet = { value ->
            _currentUserFlow.update {
                value.toUserModel().toEntity()
            }
        },
    )

    private val _currentUserFlow = MutableStateFlow(currentUserPreference.toUserModel().toEntity())
    override val currentUserFlow = _currentUserFlow.asStateFlow()

    override var currentUser: UserEntity
        get() = currentUserFlow.value
        set(value) {
            currentUserPreference = Gson().toJson(value.toModel())
        }

    companion object {
        private const val PREFERENCE_CURRENT_USER = "preference_current_user"

        fun String.toUserModel() = Gson().fromJson(this, UserModel::class.java)

        fun UserEntity.toModel() =
            UserModel(
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber,
                memberNumber = memberNumber,
                bankCode = bankCode,
            )

        fun UserModel.toEntity() =
            UserEntity(
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber,
                memberNumber = memberNumber,
                bankCode = bankCode,
            )
    }
}
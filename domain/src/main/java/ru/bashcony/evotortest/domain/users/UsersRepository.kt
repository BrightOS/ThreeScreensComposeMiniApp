package ru.bashcony.evotortest.domain.users

import kotlinx.coroutines.flow.StateFlow
import ru.bashcony.evotortest.domain.users.entity.UserEntity

interface UsersRepository {
    var currentUser: UserEntity
    val currentUserFlow: StateFlow<UserEntity>
}
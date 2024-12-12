package ru.bashcony.evotortest.domain.users.usecase

import ru.bashcony.evotortest.domain.users.UsersRepository
import ru.bashcony.evotortest.domain.users.entity.UserEntity
import javax.inject.Inject

class UpdateCurrentUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
) {
    operator fun invoke(newUser: UserEntity) {
        usersRepository.currentUser = newUser
    }
}
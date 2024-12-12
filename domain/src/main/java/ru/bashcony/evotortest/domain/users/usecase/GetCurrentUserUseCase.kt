package ru.bashcony.evotortest.domain.users.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.bashcony.evotortest.domain.users.UsersRepository
import ru.bashcony.evotortest.domain.users.entity.UserEntity
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    val usersRepository: UsersRepository,
) {
    operator fun invoke() = usersRepository.currentUserFlow
}
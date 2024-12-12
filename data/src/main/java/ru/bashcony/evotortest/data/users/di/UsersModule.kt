package ru.bashcony.evotortest.data.users.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.bashcony.evotortest.data.users.UsersRepositoryImpl
import ru.bashcony.evotortest.domain.users.UsersRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UsersModule {
    @Provides
    @Singleton
    fun provideUsersRepository(@ApplicationContext context: Context): UsersRepository =
        UsersRepositoryImpl(
            context.getSharedPreferences("evotor_test_preferences", Context.MODE_PRIVATE)
        )
}
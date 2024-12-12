package ru.bashcony.evotortest.data.cart.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.bashcony.evotortest.data.cart.CartRepositoryImpl
import ru.bashcony.evotortest.domain.cart.CartRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CartModule {
    @Provides
    @Singleton
    fun provideCartRepository(): CartRepository = CartRepositoryImpl()
}
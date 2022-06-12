package com.makhalibagas.myapplication.di

import com.makhalibagas.myapplication.data.source.repository.AuthRepository
import com.makhalibagas.myapplication.data.source.repository.MainRepository
import com.makhalibagas.myapplication.domain.repository.IAuthRepository
import com.makhalibagas.myapplication.domain.repository.IMainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAuthRepository(authRepository: AuthRepository): IAuthRepository

    @Binds
    abstract fun provideMainRepository(mainRepository: MainRepository): IMainRepository

}
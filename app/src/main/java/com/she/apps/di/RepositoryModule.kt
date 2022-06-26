package com.she.apps.di

import com.she.apps.data.source.repository.AuthRepository
import com.she.apps.data.source.repository.MainRepository
import com.she.apps.domain.repository.IAuthRepository
import com.she.apps.domain.repository.IMainRepository
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
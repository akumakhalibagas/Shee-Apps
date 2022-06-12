package com.makhalibagas.myapplication.di

import android.content.Context
import android.content.SharedPreferences
import com.makhalibagas.myapplication.utils.Shareds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesSharedPreferences(@ApplicationContext appContext: Context) =
        appContext.getSharedPreferences("shee", Context.MODE_PRIVATE)

    @Provides
    fun providesSession(sharedPreferences: SharedPreferences) = Shareds(sharedPreferences)
}
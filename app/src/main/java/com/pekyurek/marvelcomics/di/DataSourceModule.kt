package com.pekyurek.marvelcomics.di

import android.content.Context
import com.pekyurek.marvelcomics.data.repository.ApiService
import com.pekyurek.marvelcomics.data.repository.RemoteDataSource
import com.pekyurek.marvelcomics.data.repository.Repository
import com.pekyurek.marvelcomics.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideRemoteDataSource(
        @ApplicationContext context: Context,
        apiService: ApiService
    ) = RemoteDataSource(context, apiService)

    @Provides
    fun provideRepositoryImpl(
        remoteDataSource: RemoteDataSource
    ): Repository = RepositoryImpl(remoteDataSource)

}
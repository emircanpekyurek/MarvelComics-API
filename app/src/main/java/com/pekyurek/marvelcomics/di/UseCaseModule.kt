package com.pekyurek.marvelcomics.di

import com.pekyurek.marvelcomics.data.repository.Repository
import com.pekyurek.marvelcomics.domain.usecase.ComicsListUseCase
import com.pekyurek.marvelcomics.domain.usecase.character.CharacterListUseCase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideCharacterListUseCase(repository: Repository) = CharacterListUseCase(repository)

    @Provides
    fun provideComicsListUseCase(repository: Repository) = ComicsListUseCase(repository)


}
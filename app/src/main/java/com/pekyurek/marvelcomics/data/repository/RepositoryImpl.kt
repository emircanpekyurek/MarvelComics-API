package com.pekyurek.marvelcomics.data.repository

import com.pekyurek.marvelcomics.domain.model.base.ListResponse
import com.pekyurek.marvelcomics.domain.model.character.Character
import com.pekyurek.marvelcomics.domain.model.comic.Comics
import com.pekyurek.marvelcomics.domain.model.request.CharacterComicsRequest
import com.pekyurek.marvelcomics.domain.model.request.CharactersRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override fun characters(request: CharactersRequest): Flow<ResultStatus<ListResponse<Character>>> {
        return remoteDataSource.characters(request)
    }

    override fun characterComics(request: CharacterComicsRequest): Flow<ResultStatus<ListResponse<Comics>>> {
        return remoteDataSource.characterComics(request)
    }

}
package com.pekyurek.marvelcomics.data.repository

import com.pekyurek.marvelcomics.domain.model.base.ListResponse
import com.pekyurek.marvelcomics.domain.model.character.Character
import com.pekyurek.marvelcomics.domain.model.comic.Comics
import com.pekyurek.marvelcomics.domain.model.request.CharacterComicsRequest
import com.pekyurek.marvelcomics.domain.model.request.CharactersRequest
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun characters(request: CharactersRequest): Flow<ResultStatus<ListResponse<Character>>>

    fun characterComics(request: CharacterComicsRequest): Flow<ResultStatus<ListResponse<Comics>>>

}
package com.pekyurek.marvelcomics.domain.usecase

import com.pekyurek.marvelcomics.data.repository.Repository
import com.pekyurek.marvelcomics.data.repository.ResultStatus
import com.pekyurek.marvelcomics.domain.model.base.ListResponse
import com.pekyurek.marvelcomics.domain.model.comic.Comics
import com.pekyurek.marvelcomics.domain.model.request.CharacterComicsRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ComicsListUseCase @Inject constructor(private val repository: Repository) :
    ListUseCase<Comics, CharacterComicsRequest, List<Comics>>() {

    override fun execute(request: CharacterComicsRequest): Flow<ResultStatus<List<Comics>>> {
        return convertListDataToListFlow(repository.characterComics(request))
    }

}
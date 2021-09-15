package com.pekyurek.marvelcomics.domain.usecase.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pekyurek.marvelcomics.data.repository.Repository
import com.pekyurek.marvelcomics.data.repository.ResultStatus
import com.pekyurek.marvelcomics.domain.model.base.ListResponse
import com.pekyurek.marvelcomics.domain.model.character.Character
import com.pekyurek.marvelcomics.domain.model.request.CharactersRequest
import com.pekyurek.marvelcomics.domain.usecase.ListUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterListUseCase @Inject constructor(private val repository: Repository) :
    ListUseCase<Character, CharactersRequest, List<Character>>() {

    private val pageSize = 30

    fun pagingFlow(): Flow<PagingData<Character>> =
        Pager(
            config = PagingConfig(pageSize = pageSize),
            pagingSourceFactory = { CharacterPagingSource(this, pageSize) }
        ).flow


    fun executeWithListData(request: CharactersRequest): Flow<ResultStatus<ListResponse<Character>>> {
        return repository.characters(request)
    }

    override fun execute(request: CharactersRequest): Flow<ResultStatus<List<Character>>> {
        return convertListDataToListFlow(repository.characters(request))
    }
}
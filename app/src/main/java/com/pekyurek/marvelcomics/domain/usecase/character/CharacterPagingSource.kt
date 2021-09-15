package com.pekyurek.marvelcomics.domain.usecase.character

import android.content.res.Resources
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pekyurek.marvelcomics.data.repository.ResultStatus
import com.pekyurek.marvelcomics.domain.model.request.CharactersRequest
import com.pekyurek.marvelcomics.domain.model.character.Character
import kotlinx.coroutines.flow.collect

class CharacterPagingSource(
    private val characterListUseCase: CharacterListUseCase,
    private val pageSize: Int
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = params.key ?: FIRST_PAGE
        var result: LoadResult<Int, Character> = LoadResult.Error(Resources.NotFoundException())

        val characterRequest = CharactersRequest().apply {
            this.pageSize = this@CharacterPagingSource.pageSize
            pageNumber = position
        }
        characterListUseCase.executeWithListData(characterRequest).collect {
            when (it) {
                is ResultStatus.Success -> {
                    result = it.data.responseData?.let { dataList ->
                        LoadResult.Page(
                            data = dataList.results,
                            nextKey = if (position < dataList.total) dataList.offset + 1 else null,
                            prevKey = null
                        )
                    } ?: LoadResult.Error(NullPointerException())
                }

                is ResultStatus.Exception -> result =
                    LoadResult.Error(it.exception)
            }
        }
        return result
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    companion object {
        const val FIRST_PAGE = 1
    }
}
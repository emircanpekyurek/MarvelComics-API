package com.pekyurek.marvelcomics.domain.usecase

import com.pekyurek.marvelcomics.data.repository.ResultStatus
import com.pekyurek.marvelcomics.domain.model.base.ListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

abstract class ListUseCase<Model, Req, Res : List<Model>> : UseCase<Req, Res> {

    protected fun convertListDataToListFlow(resultFlow: Flow<ResultStatus<ListResponse<Model>>>): Flow<ResultStatus<List<Model>>> {
        return flow {
            resultFlow.collect {
                when (it) {
                    is ResultStatus.Success -> emit(ResultStatus.Success(it.data.responseData?.results.orEmpty()))
                    is ResultStatus.Exception -> emit(it)
                    is ResultStatus.Loading -> emit(it)
                }
            }
        }
    }

}
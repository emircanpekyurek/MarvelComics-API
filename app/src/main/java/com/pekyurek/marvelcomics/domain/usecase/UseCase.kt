package com.pekyurek.marvelcomics.domain.usecase

import com.pekyurek.marvelcomics.data.repository.ResultStatus
import kotlinx.coroutines.flow.Flow

interface UseCase<Req, Res> {

    fun execute(request: Req): Flow<ResultStatus<Res>>

}
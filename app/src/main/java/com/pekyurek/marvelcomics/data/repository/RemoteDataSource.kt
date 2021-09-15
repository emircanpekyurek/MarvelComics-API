package com.pekyurek.marvelcomics.data.repository

import android.content.Context
import com.pekyurek.marvelcomics.R
import com.pekyurek.marvelcomics.domain.exception.service.FailResponseException
import com.pekyurek.marvelcomics.domain.exception.service.NullResponseException
import com.pekyurek.marvelcomics.domain.exception.service.ServiceException
import com.pekyurek.marvelcomics.domain.model.base.BaseResponse
import com.pekyurek.marvelcomics.domain.model.base.ListResponse
import com.pekyurek.marvelcomics.domain.model.character.Character
import com.pekyurek.marvelcomics.domain.model.comic.Comics
import com.pekyurek.marvelcomics.domain.model.constants.ResponseStatus
import com.pekyurek.marvelcomics.domain.model.request.CharacterComicsRequest
import com.pekyurek.marvelcomics.domain.model.request.CharactersRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val context: Context,
    private val apiService: ApiService,
) : Repository {

    override fun characters(request: CharactersRequest): Flow<ResultStatus<ListResponse<Character>>> {
        return execute { apiService.characters(request.pageSize, request.pageNumber) }
    }

    override fun characterComics(request: CharacterComicsRequest): Flow<ResultStatus<ListResponse<Comics>>> {
        return execute { apiService.characterComics(request.characterId) }
    }

    private fun <T : BaseResponse<*>> execute(suspendResponse: suspend () -> Response<T>): Flow<ResultStatus<T>> =
        flow {
            val response = suspendResponse.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    if (it.code == 200 && it.status == ResponseStatus.STATUS_SUCCESS) {
                        emit(ResultStatus.Success(it))
                    } else {
                        emit(ResultStatus.Exception(FailResponseException(context, it.code ?: -1, it.status)))
                    }
                } ?: emit(ResultStatus.Exception(NullResponseException(context)))
            } else {
                emit(ResultStatus.Exception(FailResponseException(context,
                    response.code(),
                    response.errorBody()?.string().toString())))
            }
        }.onStart {
            emit(ResultStatus.Loading(true))
        }.catch { e ->
            emit(
                ResultStatus.Exception(
                    ServiceException(
                        e.message ?: e.localizedMessage ?: context.getString(
                            R.string.exception_service_generic_error_message
                        )
                    )
                )
            )
        }.onCompletion {
            emit(ResultStatus.Loading(false))
        }.flowOn(Dispatchers.IO)


}
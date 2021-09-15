package com.pekyurek.marvelcomics.data.repository


import com.pekyurek.marvelcomics.domain.model.base.ListResponse
import com.pekyurek.marvelcomics.domain.model.character.Character
import com.pekyurek.marvelcomics.domain.model.comic.Comics
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("/v1/public/characters")
    suspend fun characters(
        @Query("limit") pageSize: Int,
        @Query("offset") pageNumber: Int,
    ): Response<ListResponse<Character>>

    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun characterComics(@Path("characterId") characterId: Int): Response<ListResponse<Comics>>


}
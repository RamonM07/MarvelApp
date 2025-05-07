package com.heroes.marvelapp.data.remote

import com.heroes.marvelapp.data.remote.model.getCharacters.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {
    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int = 20
    ): Response<CharactersResponse>
}
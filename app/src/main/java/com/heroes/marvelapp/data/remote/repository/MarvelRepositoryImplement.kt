package com.heroes.marvelapp.data.remote.repository

import com.heroes.marvelapp.data.remote.MarvelApiService
import com.heroes.marvelapp.domain.model.getCharacters.CharactersMarvel
import com.heroes.marvelapp.domain.repository.ComicRepository

class ComicRepositoryImpl(
    private val api: MarvelApiService
): ComicRepository {
    override suspend fun getCharacters(): List<CharactersMarvel> {
        val response = api.getCharacters(
            ts = "",
            apiKey = "",
            hash = "",
            limit = 10
        )
        if (response.isSuccessful) {
            return response.body()?.data?.results?.map {
                CharactersMarvel(
                    description = it.description ?: "",
                    name = it.name ?: "",
                    id = it.id ?: 0,
                    modified = "",
                    resourceURI = ""
                )
            } ?: emptyList()
        } else {
            throw Exception("Error fetching data")
        }

    }

}

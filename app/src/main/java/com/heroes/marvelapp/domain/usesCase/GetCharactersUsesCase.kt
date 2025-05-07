package com.heroes.marvelapp.domain.usesCase

import com.heroes.marvelapp.domain.model.getCharacters.CharactersMarvel
import com.heroes.marvelapp.domain.repository.ComicRepository
import javax.inject.Inject

class GetCharactersUsesCase @Inject constructor(
    private val comicRepository: ComicRepository
) {

    suspend fun execute(): Result<List<CharactersMarvel>> {
        return try {
            val data = comicRepository.getCharacters()
            Result.success(data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

package com.heroes.marvelapp.domain.repository

import com.heroes.marvelapp.domain.model.getCharacters.CharactersMarvel

interface ComicRepository {
    suspend fun getCharacters(): List<CharactersMarvel>

}

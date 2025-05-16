package com.heroes.marvelapp.domain.model.getCharacters


data class CharactersState(
    val isLoading: Boolean = false,
    val heroes: List<CharactersMarvel> = emptyList(),
    val error: String? = null
)

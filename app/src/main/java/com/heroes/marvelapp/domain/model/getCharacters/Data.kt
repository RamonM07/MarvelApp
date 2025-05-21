package com.heroes.marvelapp.domain.model.getCharacters

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<CharactersMarvel>,
    val thumbnail: Thumbnail,
    val total: Int
)
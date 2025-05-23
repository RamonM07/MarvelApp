package com.heroes.marvelapp.domain.model.getCharacters

data class CharactersMarvel(
    val description: String = "",
    val id: Int = 0,
    val modified: String = "",
    val name: String = "",
    val resourceURI: String = "",
    val thumbnail: Thumbnail = Thumbnail("","")
)

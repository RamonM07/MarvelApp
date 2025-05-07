package com.heroes.marvelapp.domain.model.getCharacters

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)
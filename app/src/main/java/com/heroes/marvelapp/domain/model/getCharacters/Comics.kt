package com.heroes.marvelapp.domain.model.getCharacters

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)
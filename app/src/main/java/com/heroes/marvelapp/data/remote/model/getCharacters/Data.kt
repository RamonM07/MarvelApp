package com.heroes.marvelapp.data.remote.model.getCharacters


import com.google.gson.annotations.SerializedName
import com.heroes.marvelapp.domain.model.getCharacters.CharactersMarvel

data class Data(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("limit")
    val limit: Int? = 0,
    @SerializedName("offset")
    val offset: Int? = 0,
    @SerializedName("results")
    val results: List<Result>? = listOf(),
    @SerializedName("total")
    val total: Int? = 0
)
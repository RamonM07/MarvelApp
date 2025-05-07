package com.heroes.marvelapp.data.remote.model.getCharacters


import com.google.gson.annotations.SerializedName

data class Stories(
    @SerializedName("available")
    val available: Int? = 0,
    @SerializedName("collectionURI")
    val collectionURI: String? = "",
    @SerializedName("items")
    val items: List<ItemXXX>? = listOf(),
    @SerializedName("returned")
    val returned: Int? = 0
)
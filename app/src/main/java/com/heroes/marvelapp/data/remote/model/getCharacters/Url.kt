package com.heroes.marvelapp.data.remote.model.getCharacters


import com.google.gson.annotations.SerializedName

data class Url(
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("url")
    val url: String? = ""
)
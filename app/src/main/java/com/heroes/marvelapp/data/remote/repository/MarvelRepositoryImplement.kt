package com.heroes.marvelapp.data.remote.repository

import android.util.Log
import com.heroes.marvelapp.data.remote.MarvelApiService
import com.heroes.marvelapp.domain.model.getCharacters.CharactersMarvel
import com.heroes.marvelapp.domain.model.getCharacters.Thumbnail
import com.heroes.marvelapp.domain.repository.ComicRepository
import java.math.BigInteger
import java.security.MessageDigest

class ComicRepositoryImpl(
    private val api: MarvelApiService
) : ComicRepository {
    override suspend fun getCharacters(): List<CharactersMarvel> {
        val ts = 1
        val publicKey = "8e1e2be337d56845b39b94fff729f985"
        val privateKey = "75adf25e48071940236306e2da074c27b40f2c15"
        val response = api.getCharacters(
            ts = ts.toString(),
            apiKey = publicKey,
            limit = 10,
            hash = generateHash(
                timestamp = ts.toString(),
                privateKey = privateKey,
                publicKey = publicKey
            )
        )
        if (response.isSuccessful) {
            return response.body()?.data?.results?.map {
                val thumbnail = it.thumbnail
                Log.d("thumbnail:: ","$thumbnail")
                CharactersMarvel(
                    description = it.description ?: "",
                    name = it.name ?: "",
                    id = it.id ?: 0,
                    modified = "",
                    resourceURI = "",
                    thumbnail = Thumbnail(thumbnail?.extension ?: "", thumbnail?.path ?: "")
                )
            } ?: emptyList()
        } else {
            throw Exception("Error fetching data")
        }

    }

}

fun generateHash(timestamp: String, privateKey: String, publicKey: String): String {
    val input = timestamp + privateKey + publicKey
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray()))
        .toString(16)
        .padStart(32, '0')
}


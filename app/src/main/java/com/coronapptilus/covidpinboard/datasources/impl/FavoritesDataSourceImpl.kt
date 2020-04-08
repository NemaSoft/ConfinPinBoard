package com.coronapptilus.covidpinboard.datasources.impl

import android.content.SharedPreferences
import com.coronapptilus.covidpinboard.datasources.mappers.FavoritesMapper
import com.coronapptilus.covidpinboard.datasources.models.FavoritesResponseModel
import com.coronapptilus.covidpinboard.domain.models.FavoritesModel
import com.coronapptilus.covidpinboard.repositories.datasources.FavoritesDataSource
import com.google.gson.Gson

class FavoritesDataSourceImpl(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson,
    private val favoritesMapper: FavoritesMapper
) : FavoritesDataSource {

    override fun addFavorite(id: Long) {
        val favoritesJson = getFavoritesJson(id)
        with(sharedPreferences.edit()) {
            putString(FAVORITES_KEY, favoritesJson)
            commit()
        }
    }

    override fun getFavorites(): FavoritesModel {
        val favoritesJson = sharedPreferences.getString(FAVORITES_KEY, "") ?: ""
        return if (favoritesJson.isNotEmpty()) {
            val response = gson.fromJson(favoritesJson, FavoritesResponseModel::class.java)
            favoritesMapper.mapResponseToDomain(response)
        } else {
            FavoritesModel(emptyList())
        }
    }

    private fun getFavoritesJson(id: Long): String {
        val favorites: MutableList<Long> = getFavorites().favorites
                .toMutableList()
                .apply { add(id) }
        val response = FavoritesResponseModel(favorites)
        return gson.toJson(response)
    }

    companion object {
        private const val FAVORITES_KEY = "favorites"
    }
}
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
    private val mapper: FavoritesMapper
) : FavoritesDataSource {

    override fun addFavorite(announcementId: String) {
        val favoritesJson = getFavoritesJson(announcementId)
        with(sharedPreferences.edit()) {
            putString(FAVORITES_KEY, favoritesJson)
            commit()
        }
    }

    override fun getFavorites(): FavoritesModel {
        val favoritesJson = sharedPreferences.getString(FAVORITES_KEY, "") ?: ""
        return if (favoritesJson.isNotEmpty()) {
            val response = gson.fromJson(favoritesJson, FavoritesResponseModel::class.java)
            mapper.mapResponseToDomain(response)
        } else {
            FavoritesModel(emptyList())
        }
    }

    override fun removeFavorite(announcementId: String) {
        val favoritesJson = removeFavoriteFromJson(announcementId)
        with(sharedPreferences.edit()) {
            putString(FAVORITES_KEY, favoritesJson)
            commit()
        }
    }

    override fun isFavorite(announcementId: String): Boolean =
        getFavorites().favoritesAnnouncementsIds.contains(announcementId)

    private fun getFavoritesJson(announcementId: String): String {
        val favorites: MutableList<String> = getFavorites().favoritesAnnouncementsIds
            .toMutableList()
            .apply {
                if (!contains(announcementId)) {
                    add(announcementId)
                }
            }
        val response = FavoritesResponseModel(favorites)
        return gson.toJson(response)
    }

    private fun removeFavoriteFromJson(announcementId: String): String {
        val favorites: MutableList<String> = getFavorites().favoritesAnnouncementsIds
            .toMutableList()
            .apply {
                if (contains(announcementId)) {
                    remove(announcementId)
                }
            }
        val response = FavoritesResponseModel(favorites)
        return gson.toJson(response)
    }

    companion object {
        private const val FAVORITES_KEY = "favorites"
    }
}

package com.coronapptilus.covidpinboard.domain.repositories

// TODO: Add parameter and type after define parameter to save in prefs
interface FavoritesRepository {

    fun addFavorite()

    fun getFavorites()
}
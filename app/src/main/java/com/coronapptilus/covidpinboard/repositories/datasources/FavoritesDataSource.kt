package com.coronapptilus.covidpinboard.repositories.datasources

// TODO: Add parameter and type after define parameter to save in prefs
interface FavoritesDataSource {

    fun addFavorite()

    fun getFavorites()
}
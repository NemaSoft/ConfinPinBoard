package com.coronapptilus.covidpinboard.announcements.favorites

import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.domain.usecases.GetAnnouncementsByIdsUseCase
import com.coronapptilus.covidpinboard.domain.usecases.GetFavoritesUseCase
import com.coronapptilus.covidpinboard.domain.usecases.RemoveFavoriteUseCase
import kotlinx.coroutines.*

class AnnouncementsFavoritesPresenter(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val getAnnouncementsByIdsUseCase: GetAnnouncementsByIdsUseCase
) : AnnouncementsFavoritesContract.Presenter {

    override var view: AnnouncementsFavoritesContract.View? = null

    private val job = SupervisorJob()
    private val errorHandler = CoroutineExceptionHandler { _, _ -> }
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main + errorHandler)

    override fun attachView(newView: AnnouncementsFavoritesContract.View) {
        view = newView
    }

    override fun detachView() {
        view = null
        coroutineScope.cancel()
    }

    override fun init() {
        getFavorites()
    }

    override fun removeFavorite(id: String) {
        removeFavoriteUseCase.execute(id)
        getFavorites()
    }

    private fun getFavorites() {
        coroutineScope.launch {
            var favorites: List<AnnouncementModel> = emptyList()
            val favoritesIds = getFavoritesUseCase.execute().favorites
            favoritesIds.takeIf { it.isNotEmpty() }?.let { ids ->
                withContext(Dispatchers.IO) {
                    val response = getAnnouncementsByIdsUseCase.execute(ids)
                    if (response is ResponseState.Success) {
                        favorites = response.result
                    }
                }
            }
            view?.update(favorites)
        }
    }
}
package com.coronapptilus.covidpinboard.announcements.favorites

import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.domain.usecases.GetAnnouncementsByIdsUseCase
import com.coronapptilus.covidpinboard.domain.usecases.GetFavoritesUseCase
import kotlinx.coroutines.*

class AnnouncementsFavoritesPresenter(
    private val getFavoritesUseCase: GetFavoritesUseCase,
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

    override fun onAnnouncementItemClicked(announcement: AnnouncementModel) {
        view?.showAnnouncementDetail(announcement)
    }

    override fun onFavoriteStatusChanged() {
        view?.hideAnnouncementDetail()
    }

    private fun getFavorites() {
        coroutineScope.launch {
            val favoriteAnnouncements: List<AnnouncementModel> = getFavoritesUseCase.execute()
                .favoritesAnnouncementsIds
                .takeIf { it.isNotEmpty() }
                ?.let { announcementsIds ->
                    withContext(Dispatchers.IO) {
                        val response = getAnnouncementsByIdsUseCase.execute(announcementsIds)
                        (response as? ResponseState.Success)?.result
                    }
                } ?: emptyList()
            view?.update(favoriteAnnouncements)
        }
    }
}

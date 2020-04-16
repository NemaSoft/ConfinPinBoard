package com.confinapptilus.confinpinboard.announcements.favorites

import com.confinapptilus.confinpinboard.datasources.ResponseState
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel
import com.confinapptilus.confinpinboard.domain.usecases.GetAnnouncementsUseCase
import com.confinapptilus.confinpinboard.domain.usecases.GetFavoritesUseCase
import kotlinx.coroutines.*

class AnnouncementsFavoritesPresenter(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val getAnnouncementsUseCase: GetAnnouncementsUseCase
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

    override fun onAnnouncementItemClicked(announcement: AnnouncementModel) {
        view?.showAnnouncementDetail(announcement)
    }

    override fun onFavoriteStatusChanged() {
        view?.hideAnnouncementDetail()
        getFavorites()
    }

    override fun getFavorites(
        searchTerm: String,
        categories: List<AnnouncementModel.Category>
    ) {
        view?.showProgress()
        coroutineScope.launch {
            val favoriteAnnouncements: List<AnnouncementModel> = getFavoritesUseCase.execute()
                .favoritesAnnouncementsIds
                .takeIf { it.isNotEmpty() }
                ?.let { announcementsIds ->
                    withContext(Dispatchers.IO) {
                        val response = getAnnouncementsUseCase.execute(
                            announcementsIds,
                            searchTerm,
                            categories
                        )
                        (response as? ResponseState.Success)?.result
                    }
                } ?: emptyList()
            view?.update(favoriteAnnouncements)
            view?.hideProgress()
        }
    }
}

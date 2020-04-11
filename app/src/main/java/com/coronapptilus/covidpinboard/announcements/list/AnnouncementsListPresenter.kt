package com.coronapptilus.covidpinboard.announcements.list

import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.domain.usecases.AddFavoriteUseCase
import com.coronapptilus.covidpinboard.domain.usecases.GetAnnouncementsUseCase
import com.coronapptilus.covidpinboard.domain.usecases.IsFavoriteUseCase
import com.coronapptilus.covidpinboard.domain.usecases.RemoveFavoriteUseCase
import kotlinx.coroutines.*

class AnnouncementsListPresenter(
    private val getAnnouncementsUseCase: GetAnnouncementsUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : AnnouncementsListContract.Presenter {

    override var view: AnnouncementsListContract.View? = null

    private val job = SupervisorJob()
    private val errorHandler = CoroutineExceptionHandler { _, _ -> }
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main + errorHandler)

    override fun attachView(newView: AnnouncementsListContract.View) {
        view = newView
    }

    override fun detachView() {
        view = null
        coroutineScope.cancel()
    }

    override fun init() {
        getAnnouncements()
    }

    private fun getAnnouncements() {
        coroutineScope.launch {
            var announcements: List<AnnouncementModel> = emptyList()
            withContext(Dispatchers.IO) {
                val response = getAnnouncementsUseCase.execute()
                if (response is ResponseState.Success) {
                    announcements = response.result
                }
            }
            view?.update(announcements)
        }
    }

    override fun onAnnouncementItemClicked(announcement: AnnouncementModel) {
        val isFavorite = isFavoriteUseCase.execute(announcement.id)
        view?.showAnnouncementDetail(announcement, isFavorite)
    }

    override fun onFavoriteButtonClicked(announcementId: String, isFavoriteSelected: Boolean) {
        if (isFavoriteSelected) {
            addFavoriteUseCase.execute(announcementId)
        } else {
            removeFavoriteUseCase.execute(announcementId)
        }
    }
}

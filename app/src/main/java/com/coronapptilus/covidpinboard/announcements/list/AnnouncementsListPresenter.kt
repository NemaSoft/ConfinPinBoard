package com.coronapptilus.covidpinboard.announcements.list

import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.domain.usecases.GetAnnouncementsUseCase
import kotlinx.coroutines.*

class AnnouncementsListPresenter(private val getAnnouncementsUseCase: GetAnnouncementsUseCase) :
    AnnouncementsListContract.Presenter {

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

    override fun getAnnouncements(
        searchTerm: String,
        categories: List<AnnouncementModel.Category>
    ) {
        coroutineScope.launch {
            var announcements: List<AnnouncementModel> = emptyList()
            withContext(Dispatchers.IO) {
                val response = getAnnouncementsUseCase.execute(
                    searchTerm = searchTerm,
                    categories = categories
                )
                if (response is ResponseState.Success) {
                    announcements = response.result
                }
            }
            view?.update(announcements)
        }
    }

    override fun onAnnouncementItemClicked(announcement: AnnouncementModel) {
        view?.showAnnouncementDetail(announcement)
    }
}

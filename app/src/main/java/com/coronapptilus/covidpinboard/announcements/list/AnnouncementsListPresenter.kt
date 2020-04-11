package com.coronapptilus.covidpinboard.announcements.list

import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.domain.usecases.GetAnnouncementsUseCase
import com.coronapptilus.covidpinboard.utils.CategoryUtils
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
            view?.update(listOf(mockAnnouncementModel))
        }
    }

    override fun onAnnouncementItemClicked(announcement: AnnouncementModel) {
        view?.showAnnouncementDetail(announcement)
    }


    private val categories = emptyList<AnnouncementModel.Category>()
    private val target = AnnouncementModel.Target.Undefined


    // TODO: Check different casuistics with this mockk model
    private val mockAnnouncementModel = AnnouncementModel(
        id = "123",
        announcer = "",
        title = "",
        description = "",
        place = "",
        categories = categories,
        target = target,
        startDate = "",
        startTime = "",
        endDate = "",
        endTime = ""
    )

}

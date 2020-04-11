package com.coronapptilus.covidpinboard.announcements.list

import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.domain.usecases.GetAnnouncementsUseCase
import kotlinx.coroutines.*

class AnnouncementsListPresenter(
    private val getAnnouncementsUseCase: GetAnnouncementsUseCase
): AnnouncementsListContract.Presenter {

    override var view: AnnouncementsListContract.View? = null

    private val job = SupervisorJob()
    private val errorHandler = CoroutineExceptionHandler { _, _ ->  }
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

    private val categories = listOf<AnnouncementModel.Category>(AnnouncementModel.Category.Sport)
    private val target = AnnouncementModel.Target.Family

    // TODO: Check different casuistics with this mockk model
    private val mockAnnouncementModel = AnnouncementModel(
        id = "123",
        announcer = "Pepe",
        title = "Hola",
        description = "asdasfasdasd",
        place = "asdasd",
        categories = categories,
        target = target,
        startDate = "12/07/2020",
        startTime = "12:00",
        endDate = "12/07/2020",
        endTime = "13:00"
    )
}
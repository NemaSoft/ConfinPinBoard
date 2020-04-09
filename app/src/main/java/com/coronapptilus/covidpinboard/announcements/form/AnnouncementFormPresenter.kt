package com.coronapptilus.covidpinboard.announcements.form

import com.coronapptilus.covidpinboard.commons.base.BaseContract
import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.domain.usecases.AddAnnouncementUseCase
import kotlinx.coroutines.*
import java.util.*

class AnnouncementFormPresenter(
    private val addAnnouncementUseCase: AddAnnouncementUseCase
) : AnnouncementFormContract.Presenter {

    override var view: AnnouncementFormContract.View? = null

    private val job = SupervisorJob()
    private val errorHandler = CoroutineExceptionHandler { _, _ -> }
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main + errorHandler)

    override fun attachView(newView: AnnouncementFormContract.View) {
        view = newView
    }

    override fun detachView() {
        view = null
        coroutineScope.cancel()
    }

    override fun addAnnouncement() {
        coroutineScope.launch {
            val announcement = AnnouncementModel(
                id = UUID.randomUUID().toString(),
                announcer = "Flo",
                title = "Directo con Dani",
                description = "Directo de Instagram con Dani para hablar de la vida",
                place = "https://www.instagram.com/flofdezz/",
                categories = listOf(
                    AnnouncementModel.Category.Interview,
                    AnnouncementModel.Category.Others
                ),
                target = AnnouncementModel.Target.Family,
                startDate = "13/04/2020",
                startTime = "18:00",
                endDate = "13/04/2020",
                endTime = "19:00"
            )

            withContext(Dispatchers.IO) {
                val response = addAnnouncementUseCase.execute(announcement)
                if (response is ResponseState.Success) {
                    // Announcement was added
                }
            }
        }
    }
}
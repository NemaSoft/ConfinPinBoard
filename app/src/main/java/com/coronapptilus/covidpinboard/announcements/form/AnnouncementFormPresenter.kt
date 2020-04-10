package com.coronapptilus.covidpinboard.announcements.form

import com.coronapptilus.covidpinboard.domain.usecases.AddAnnouncementUseCase
import kotlinx.coroutines.*

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
            // TODO("Create announcement from form data")
            //val announcement = AnnouncementModel()

            withContext(Dispatchers.IO) {
//                val response = addAnnouncementUseCase.execute(announcement)
//                if (response is ResponseState.Success) {
                    // Announcement was added
//                }
            }
        }
    }
}
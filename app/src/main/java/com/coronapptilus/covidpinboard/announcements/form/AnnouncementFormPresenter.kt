package com.coronapptilus.covidpinboard.announcements.form

import android.content.Context
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
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

    override fun getSpinnerTargetList(context: Context): List<String> {
        val targetList: List<AnnouncementModel.Target> = listOf(
            AnnouncementModel.Target.Adults,
            AnnouncementModel.Target.Children,
            AnnouncementModel.Target.Familiar
        )
        val targetListNames = targetList.mapNotNull {
            if (it.name != null) {
                context.getString(it.name)
            } else {
                null
            }
        }.toMutableList()

        targetListNames.add(0, context.getString(R.string.chooseATarget))
        return targetListNames
    }

    override fun submitForm() {
        if (!validateForm()) return

        // TODO
    }

    override fun validateForm(): Boolean {
        // TODO
        return false
    }
}
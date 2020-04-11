package com.coronapptilus.covidpinboard.announcements.form

import android.content.Context
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.domain.usecases.AddAnnouncementUseCase
import com.coronapptilus.covidpinboard.utils.FormValidator
import kotlinx.coroutines.*
import java.util.*

class AnnouncementFormPresenter(
    private val addAnnouncementUseCase: AddAnnouncementUseCase
) : AnnouncementFormContract.Presenter {

    override var view: AnnouncementFormContract.View? = null
    private var targetList: List<AnnouncementModel.Target> = listOf()

    companion object {
        const val TARGET_EXTRA_POSITION = 1
    }

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

    override fun addAnnouncement(announcement: AnnouncementModel) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                val response = addAnnouncementUseCase.execute(announcement)
                if (response is ResponseState.Success) {
//                 Announcement was added
                    view?.showMessage("Mensaje respuesta") // TODO ESPECIFICAR MENSAJE. Validaciones?
                    view?.navigateToBoardFragment()
                } else {
                    view?.showMessage("Mensaje respuesta") // TODO ESPECIFICAR MENSAJE. Validaciones?
                }

                view?.navigateToBoardFragment() //TODO eliminar. sólo para pruebas mientras no esté el
                view?.hideProgress()
            }
        }
    }

    override fun getSpinnerTargetList(context: Context): List<String> {
        this.targetList = listOf(
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

    override fun getTargetType(targetPosition: Int): AnnouncementModel.Target? {
        if (this.targetList.isNotEmpty() && targetPosition > 0) {
            return this.targetList[targetPosition - TARGET_EXTRA_POSITION]
        }

        return null
    }

    override fun submitForm(
        announcer: String,
        title: String,
        description: String,
        place: String,
        categories: List<AnnouncementModel.Category>,
        target: AnnouncementModel.Target?,
        startingDate: String,
        startingTime: String,
        endingDate: String,
        endingTime: String
    ) {

        view?.showProgress()


        if (!isValidForm(
                announcer,
                title,
                description,
                place,
                categories,
                target,
                startingDate,
                startingTime,
                endingDate,
                endingTime
            )
        ) {
            view?.hideProgress()
            return
        }
        val id = UUID.randomUUID().toString()

        val announcement = AnnouncementModel(
            id,
            announcer,
            title,
            description,
            place,
            categories,
            target!!,
            startingDate,
            startingTime,
            endingDate,
            endingTime
        )

        addAnnouncement(announcement)
    }

    private fun isValidForm(
        announcer: String,
        title: String,
        description: String,
        place: String,
        categories: List<AnnouncementModel.Category>,
        target: AnnouncementModel.Target?,
        startingDate: String,
        startingTime: String,
        endingDate: String,
        endingTime: String
    ): Boolean {

        return FormValidator.isValidAnnouncer(announcer)
                && FormValidator.isValidTitle(title)
                && FormValidator.isValidPlace(place)
                && FormValidator.isValidDescription(description)
                && FormValidator.isValidTarget(target)
                && FormValidator.isValidCategoryList(categories)
                && FormValidator.isValidDate(startingDate, startingTime, endingDate, endingTime)
    }
}
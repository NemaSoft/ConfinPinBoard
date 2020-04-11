package com.coronapptilus.covidpinboard.announcements.detail

import com.coronapptilus.covidpinboard.commons.extensions.convertDateToTimestamp
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.domain.usecases.AddFavoriteUseCase
import com.coronapptilus.covidpinboard.domain.usecases.IsFavoriteUseCase
import com.coronapptilus.covidpinboard.domain.usecases.RemoveFavoriteUseCase

class AnnouncementDetailPresenter(
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : AnnouncementDetailContract.Presenter {

    override var view: AnnouncementDetailContract.View? = null
    private var announcement: AnnouncementModel? = null

    override fun attachView(newView: AnnouncementDetailContract.View) {
        view = newView
    }

    override fun detachView() {
        view = null
    }

    override fun init(announcement: AnnouncementModel) {
        this.announcement = announcement
        val isFavorite = isFavoriteUseCase.execute(announcement.id)
        view?.updateFavoriteButtonStatus(isFavorite)
    }

    override fun onFavoriteButtonClicked(isFavoriteSelected: Boolean) {
        if (isFavoriteSelected) {
            announcement?.let { addFavoriteUseCase.execute(it.id) }
        } else {
            announcement?.let { removeFavoriteUseCase.execute(it.id) }
        }
    }

    override fun onCalendarButtonClicked() {
        announcement?.run {
            view?.launchCalendarIntent(
                title,
                description,
                place,
                convertDateToTimestamp(startDate, startTime) ?: 0L,
                convertDateToTimestamp(endDate, endTime) ?: 0L
            )
        }
    }

    override fun onCloseButtonClicked() {
        view?.exitView()
    }
}

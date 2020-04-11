package com.coronapptilus.covidpinboard.announcements.form

import android.content.Context
import com.coronapptilus.covidpinboard.commons.base.BaseContract
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel

interface AnnouncementFormContract {

    interface View : BaseContract.View {

        fun showProgress()

        fun hideProgress()

        fun showMessage(message: String)

        fun navigateToBoardFragment()

    }

    interface Presenter : BaseContract.Presenter<View> {

        fun getSpinnerTargetList(context: Context): List<String>

        fun getTargetType(targetPosition: Int): AnnouncementModel.Target?

        fun submitForm(
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
        )

        fun addAnnouncement(announcement: AnnouncementModel)
    }
}
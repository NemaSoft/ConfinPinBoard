package com.confinapptilus.confinpinboard.announcements.form

import android.content.Context
import com.confinapptilus.confinpinboard.commons.base.BaseContract
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel

interface AnnouncementFormContract {

    interface View : BaseContract.View {

        fun showProgress()

        fun hideProgress()

        fun showMessage(message: Int)

        fun setErrorMessage(message: Int, formItem: FormItem)

        fun clearForm()
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
package com.coronapptilus.covidpinboard.announcements.detail

import com.coronapptilus.covidpinboard.commons.base.BaseContract
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel

interface AnnouncementDetailContract {

    interface View : BaseContract.View {

        fun updateFavoriteButtonStatus(isFavorite: Boolean)

        fun launchCalendarIntent(
            title: String,
            description: String,
            place: String,
            startTimestamp: Long,
            endTimestamp: Long
        )

        fun notifyFavoriteStatusChanged()

        fun exitView()
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun init(announcement: AnnouncementModel)

        fun onFavoriteButtonClicked(isFavoriteSelected: Boolean)

        fun onCalendarButtonClicked()

        fun onCloseButtonClicked()
    }
}

package com.confinapptilus.confinpinboard.announcements.detail

import com.confinapptilus.confinpinboard.commons.base.BaseContract
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel

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

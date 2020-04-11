package com.coronapptilus.covidpinboard.announcements.list

import com.coronapptilus.covidpinboard.commons.base.BaseContract
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel

interface AnnouncementsListContract {

    interface View : BaseContract.View {

        fun update(announcements: List<AnnouncementModel>)

        fun showAnnouncementDetail(announcement: AnnouncementModel, isFavorite: Boolean)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun init()

        fun onAnnouncementItemClicked(announcement: AnnouncementModel)

        fun onFavoriteButtonClicked(announcementId: String, isFavoriteSelected: Boolean)
    }
}

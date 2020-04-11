package com.coronapptilus.covidpinboard.announcements.favorites

import com.coronapptilus.covidpinboard.commons.base.BaseContract
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel

interface AnnouncementsFavoritesContract {

    interface View : BaseContract.View {

        fun update(favorites: List<AnnouncementModel>)

        fun showAnnouncementDetail(announcement: AnnouncementModel)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun init()

        fun onAnnouncementItemClicked(announcement: AnnouncementModel)
    }
}

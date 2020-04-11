package com.coronapptilus.covidpinboard.announcements.list

import com.coronapptilus.covidpinboard.commons.base.BaseContract
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel

interface AnnouncementsListContract {

    interface View : BaseContract.View {

        fun update(announcements: List<AnnouncementModel>)

        fun showAnnouncementDetail(announcement: AnnouncementModel)

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun onAnnouncementItemClicked(announcement: AnnouncementModel)

        fun getAnnouncements(
            searchTerm: String = "",
            categories: List<AnnouncementModel.Category> = emptyList()
        )
    }
}

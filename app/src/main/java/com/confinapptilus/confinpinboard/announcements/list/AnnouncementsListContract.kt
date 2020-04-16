package com.confinapptilus.confinpinboard.announcements.list

import com.confinapptilus.confinpinboard.commons.base.BaseContract
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel

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

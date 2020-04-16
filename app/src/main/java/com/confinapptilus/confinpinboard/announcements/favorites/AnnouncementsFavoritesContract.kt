package com.confinapptilus.confinpinboard.announcements.favorites

import com.confinapptilus.confinpinboard.commons.base.BaseContract
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel

interface AnnouncementsFavoritesContract {

    interface View : BaseContract.View {

        fun update(favorites: List<AnnouncementModel>)

        fun showAnnouncementDetail(announcement: AnnouncementModel)

        fun hideAnnouncementDetail()

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun getFavorites(
            searchTerm: String = "",
            categories: List<AnnouncementModel.Category> = emptyList()
        )

        fun onAnnouncementItemClicked(announcement: AnnouncementModel)

        fun onFavoriteStatusChanged()
    }
}

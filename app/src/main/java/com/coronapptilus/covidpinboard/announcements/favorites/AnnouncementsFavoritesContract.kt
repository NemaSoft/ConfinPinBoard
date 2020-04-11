package com.coronapptilus.covidpinboard.announcements.favorites

import com.coronapptilus.covidpinboard.commons.base.BaseContract
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel

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

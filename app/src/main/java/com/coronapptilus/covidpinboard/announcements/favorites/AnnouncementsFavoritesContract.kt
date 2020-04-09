package com.coronapptilus.covidpinboard.announcements.favorites

import com.coronapptilus.covidpinboard.commons.base.BaseContract

interface AnnouncementsFavoritesContract {

    interface View : BaseContract.View {}

    interface Presenter : BaseContract.Presenter<View> {}
}
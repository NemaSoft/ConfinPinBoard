package com.coronapptilus.covidpinboard.announcements.list

import com.coronapptilus.covidpinboard.commons.base.BaseContract

interface AnnouncementsListContract {

    interface View : BaseContract.View {}

    interface Presenter : BaseContract.Presenter<View> {}
}
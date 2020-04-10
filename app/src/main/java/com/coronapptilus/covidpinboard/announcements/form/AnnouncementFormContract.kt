package com.coronapptilus.covidpinboard.announcements.form

import com.coronapptilus.covidpinboard.commons.base.BaseContract

interface AnnouncementFormContract {

    interface View : BaseContract.View {}

    interface Presenter : BaseContract.Presenter<View> {

        fun addAnnouncement()
    }
}
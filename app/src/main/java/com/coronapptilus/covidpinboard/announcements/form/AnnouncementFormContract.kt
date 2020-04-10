package com.coronapptilus.covidpinboard.announcements.form

import com.coronapptilus.covidpinboard.commons.base.BaseContract

interface AnnouncementFormContract {

    interface View : BaseContract.View {

        fun setupPickersViews()

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun submitForm()

        fun validateForm(): Boolean
    }
}
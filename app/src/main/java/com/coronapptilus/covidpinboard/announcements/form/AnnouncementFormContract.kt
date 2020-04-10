package com.coronapptilus.covidpinboard.announcements.form

import android.content.Context
import com.coronapptilus.covidpinboard.commons.base.BaseContract

interface AnnouncementFormContract {

    interface View : BaseContract.View {

        fun setupPickersViews()

        fun showProgress()

        fun hideProgress()

        fun setupSpinnerView()
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun getSpinnerTargetList(context: Context): List<String>

        fun submitForm()

        fun validateForm(): Boolean
    }
}
package com.coronapptilus.covidpinboard.announcements.commons.filter

import com.coronapptilus.covidpinboard.commons.base.BaseContract

interface FilterContract {

    interface View : BaseContract.View {}

    interface Presenter : BaseContract.Presenter<View> {}

}
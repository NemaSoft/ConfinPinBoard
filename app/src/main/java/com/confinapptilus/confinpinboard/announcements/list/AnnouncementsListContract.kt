package com.confinapptilus.confinpinboard.announcements.list

import com.confinapptilus.confinpinboard.commons.base.BaseContract

interface AnnouncementsListContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View>
}

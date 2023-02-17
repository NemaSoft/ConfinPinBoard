package com.confinapptilus.confinpinboard.announcements.list

class AnnouncementsListPresenter : AnnouncementsListContract.Presenter {

    override var view: AnnouncementsListContract.View? = null

    override fun attachView(newView: AnnouncementsListContract.View) {
        view = newView
    }

    override fun detachView() {
        view = null
    }
}

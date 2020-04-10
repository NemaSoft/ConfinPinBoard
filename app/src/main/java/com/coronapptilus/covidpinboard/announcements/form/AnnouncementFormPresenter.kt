package com.coronapptilus.covidpinboard.announcements.form

class AnnouncementFormPresenter : AnnouncementFormContract.Presenter {

    override var view: AnnouncementFormContract.View? = null

    override fun attachView(newView: AnnouncementFormContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun submitForm() {
        if (!validateForm()) return

        // TODO
    }

    override fun validateForm(): Boolean {
        // TODO
        return false
    }
}
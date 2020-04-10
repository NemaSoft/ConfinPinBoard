package com.coronapptilus.covidpinboard.announcements.form

import android.content.Context
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel

class AnnouncementFormPresenter : AnnouncementFormContract.Presenter {

    override var view: AnnouncementFormContract.View? = null

    override fun attachView(newView: AnnouncementFormContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun getSpinnerTargetList(context: Context): List<String> {
        val targetList: List<AnnouncementModel.Target> = listOf(
            AnnouncementModel.Target.Adults,
            AnnouncementModel.Target.Children,
            AnnouncementModel.Target.Familiar
        )
        val targetListNames = targetList.map {
            context.getString(it.name)
        }.toMutableList()

        targetListNames.add(0, context.getString(R.string.chooseATarget))
        return targetListNames
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
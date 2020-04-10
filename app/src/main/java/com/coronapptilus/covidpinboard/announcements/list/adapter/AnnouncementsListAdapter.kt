package com.coronapptilus.covidpinboard.announcements.list.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.commons.base.BaseRecyclerViewAdapter
import com.coronapptilus.covidpinboard.utils.DetailDialogUtils
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import kotlinx.android.synthetic.main.detail_dialog.view.*

class AnnouncementsListAdapter :

    private fun showDetailDialog(item: AnnouncementModel, parent: ViewGroup){

        val dialogView = LayoutInflater.from(parent.context).inflate(R.layout.detail_dialog,null)

        val filledFialog = DetailDialogUtils.getFilledDialog(item, dialogView)

        val mBuilder = AlertDialog.Builder(parent.context).setView(filledFialog).show()

        dialogView.dialog_favorites_button.setOnClickListener {  }

    }

}
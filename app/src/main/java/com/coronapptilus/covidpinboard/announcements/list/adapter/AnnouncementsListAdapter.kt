package com.coronapptilus.covidpinboard.announcements.list.adapter

import android.view.ViewGroup
import com.coronapptilus.covidpinboard.commons.base.BaseRecyclerViewAdapter
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel

class AnnouncementsListAdapter :
    BaseRecyclerViewAdapter<AnnouncementModel, AnnouncementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder =
        AnnouncementViewHolder(parent)
}

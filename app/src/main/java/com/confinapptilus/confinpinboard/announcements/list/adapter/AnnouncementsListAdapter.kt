package com.confinapptilus.confinpinboard.announcements.list.adapter

import android.view.ViewGroup
import com.confinapptilus.confinpinboard.commons.base.BaseRecyclerViewAdapter
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel

class AnnouncementsListAdapter :
    BaseRecyclerViewAdapter<AnnouncementModel, AnnouncementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder =
        AnnouncementViewHolder(parent)
}

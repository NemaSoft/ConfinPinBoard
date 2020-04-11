package com.coronapptilus.covidpinboard.domain.repositories

import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel

interface AnnouncementsRepository {

    suspend fun addAnnouncement(announcement: AnnouncementModel): ResponseState<String>

    suspend fun getAnnouncements(
        ids: List<String>,
        categories: List<AnnouncementModel.Category>
    ): ResponseState<List<AnnouncementModel>>
}
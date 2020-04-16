package com.confinapptilus.confinpinboard.domain.repositories

import com.confinapptilus.confinpinboard.datasources.ResponseState
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel

interface AnnouncementsRepository {

    suspend fun addAnnouncement(announcement: AnnouncementModel): ResponseState<String>

    suspend fun getAnnouncements(
        ids: List<String>,
        searchTerm: String,
        categories: List<AnnouncementModel.Category>
    ): ResponseState<List<AnnouncementModel>>
}
package com.confinapptilus.confinpinboard.repositories.datasources

import com.confinapptilus.confinpinboard.datasources.ResponseState
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel

interface AnnouncementsDataSource {

    suspend fun addAnnouncement(announcement: AnnouncementModel): ResponseState<String>

    suspend fun getAnnouncements(
        ids: List<String>,
        searchTerm: String,
        categories: List<AnnouncementModel.Category>
    ): ResponseState<List<AnnouncementModel>>
}
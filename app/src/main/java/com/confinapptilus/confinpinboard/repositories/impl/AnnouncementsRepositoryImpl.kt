package com.confinapptilus.confinpinboard.repositories.impl

import com.confinapptilus.confinpinboard.datasources.ResponseState
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel
import com.confinapptilus.confinpinboard.domain.repositories.AnnouncementsRepository
import com.confinapptilus.confinpinboard.repositories.datasources.AnnouncementsDataSource

class AnnouncementsRepositoryImpl(private val dataSource: AnnouncementsDataSource) :
    AnnouncementsRepository {

    override suspend fun addAnnouncement(announcement: AnnouncementModel): ResponseState<String> =
        dataSource.addAnnouncement(announcement)

    override suspend fun getAnnouncements(
        ids: List<String>,
        searchTerm: String,
        categories: List<AnnouncementModel.Category>
    ): ResponseState<List<AnnouncementModel>> =
        dataSource.getAnnouncements(ids, searchTerm, categories)
}
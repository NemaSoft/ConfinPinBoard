package com.coronapptilus.covidpinboard.repositories.impl

import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.domain.repositories.AnnouncementsRepository
import com.coronapptilus.covidpinboard.repositories.datasources.AnnouncementsDataSource

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
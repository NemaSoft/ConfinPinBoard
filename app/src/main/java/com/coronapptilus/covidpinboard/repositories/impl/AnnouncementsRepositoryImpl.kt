package com.coronapptilus.covidpinboard.repositories.impl

import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.domain.repositories.AnnouncementsRepository
import com.coronapptilus.covidpinboard.repositories.datasources.AnnouncementsDataSource

class AnnouncementsRepositoryImpl(private val dataSource: AnnouncementsDataSource): AnnouncementsRepository {

    override suspend fun addAnnouncement(announcement: AnnouncementModel): ResponseState<Nothing> =
        dataSource.addAnnouncement(announcement)

    override suspend fun getAnnouncement(id: Long): ResponseState<AnnouncementModel> =
        dataSource.getAnnouncement(id)

    override suspend fun getAnnouncements(): ResponseState<List<AnnouncementModel>> =
        dataSource.getAnnouncements()
}
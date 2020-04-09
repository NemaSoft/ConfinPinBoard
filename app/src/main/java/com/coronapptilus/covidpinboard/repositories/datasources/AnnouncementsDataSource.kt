package com.coronapptilus.covidpinboard.repositories.datasources

import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel

interface AnnouncementsDataSource {

    suspend fun addAnnouncement(announcement: AnnouncementModel): ResponseState<Nothing>

    suspend fun getAnnouncement(id: Long): ResponseState<AnnouncementModel>

    suspend fun getAnnouncements(): ResponseState<List<AnnouncementModel>>
}
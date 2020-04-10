package com.coronapptilus.covidpinboard.repositories.datasources

import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel

interface AnnouncementsDataSource {

    suspend fun addAnnouncement(announcement: AnnouncementModel): ResponseState<String>

    suspend fun getAnnouncementsByIds(ids: List<String>): ResponseState<List<AnnouncementModel>>

    suspend fun getAnnouncements(): ResponseState<List<AnnouncementModel>>
}
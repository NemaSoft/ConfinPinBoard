package com.coronapptilus.covidpinboard.datasources.impl

import com.coronapptilus.covidpinboard.commons.extensions.safeCall
import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.datasources.api.AnnouncementsApi
import com.coronapptilus.covidpinboard.datasources.mappers.AnnouncementMapper
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.repositories.datasources.AnnouncementsDataSource

class AnnouncementsDataSourceImpl(
    private val announcementsApi: AnnouncementsApi,
    private val mapper: AnnouncementMapper
) : AnnouncementsDataSource {

    override suspend fun addAnnouncement(announcement: AnnouncementModel): ResponseState<Nothing> =
        safeCall {
            val response = mapper.mapDomainToResponse(announcement)
            announcementsApi.addAnnouncement(response)
        }

    override suspend fun getAnnouncement(id: Long): ResponseState<AnnouncementModel>  =
        safeCall { mapper.mapResponseToDomain(announcementsApi.getAnnouncement(id)) }

    override suspend fun getAnnouncements(): ResponseState<List<AnnouncementModel>> =
        safeCall {
            announcementsApi.getAnnouncements()
                ?.filterNotNull()
                ?.map { mapper.mapResponseToDomain(it) } ?: emptyList()
        }

    private fun existsContent(title: String?, description: String?, place: String?): Boolean =
        title != null && title.isNotEmpty()
                && description != null && description.isNotEmpty()
                && place != null && place.isNotEmpty()
}
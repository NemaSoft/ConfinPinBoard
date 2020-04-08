package com.coronapptilus.covidpinboard.datasources.api

import com.coronapptilus.covidpinboard.datasources.models.AnnouncementResponseModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface AnnouncementsApi {

    @GET("")
    suspend fun addAnnouncement(@Body announcement: AnnouncementResponseModel): Nothing

    @GET("")
    suspend fun getAnnouncement(@Path("id") id: Long): AnnouncementResponseModel?

    @GET("")
    suspend fun getAnnouncements(): List<AnnouncementResponseModel?>?
}
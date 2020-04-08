package com.coronapptilus.covidpinboard.datasources.api

import com.coronapptilus.covidpinboard.datasources.models.AnnouncementResponseModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// TODO("Add firebase database url info when it is ready")
interface AnnouncementsApi {

    @POST("/announcements")
    suspend fun addAnnouncement(@Body announcement: AnnouncementResponseModel): Nothing

    @GET("/announcements/{id}")
    suspend fun getAnnouncement(@Path("id") id: Long): AnnouncementResponseModel?

    @GET("/announcements")
    suspend fun getAnnouncements(): List<AnnouncementResponseModel?>?
}

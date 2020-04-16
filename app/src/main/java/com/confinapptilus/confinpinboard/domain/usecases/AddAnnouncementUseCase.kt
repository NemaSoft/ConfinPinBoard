package com.confinapptilus.confinpinboard.domain.usecases

import com.confinapptilus.confinpinboard.datasources.ResponseState
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel
import com.confinapptilus.confinpinboard.domain.repositories.AnnouncementsRepository

class AddAnnouncementUseCase(private val repository: AnnouncementsRepository) {

    suspend fun execute(announcement: AnnouncementModel): ResponseState<String> =
        repository.addAnnouncement(announcement)
}
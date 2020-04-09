package com.coronapptilus.covidpinboard.domain.usecases

import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.domain.repositories.AnnouncementsRepository

class GetAnnouncementsUseCase(private val repository: AnnouncementsRepository) {

    suspend fun execute(): ResponseState<List<AnnouncementModel>> = repository.getAnnouncements()
}
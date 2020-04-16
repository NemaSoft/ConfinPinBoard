package com.confinapptilus.confinpinboard.domain.usecases

import com.confinapptilus.confinpinboard.datasources.ResponseState
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel
import com.confinapptilus.confinpinboard.domain.repositories.AnnouncementsRepository

class GetAnnouncementsUseCase(private val repository: AnnouncementsRepository) {

    suspend fun execute(
        ids: List<String> = emptyList(),
        searchTerm: String = "",
        categories: List<AnnouncementModel.Category> = emptyList()
    ): ResponseState<List<AnnouncementModel>> =
        repository.getAnnouncements(ids, searchTerm, categories)
}
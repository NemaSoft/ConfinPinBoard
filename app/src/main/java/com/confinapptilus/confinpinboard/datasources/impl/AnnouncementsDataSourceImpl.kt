package com.confinapptilus.confinpinboard.datasources.impl

import com.confinapptilus.confinpinboard.commons.extensions.isAvailable
import com.confinapptilus.confinpinboard.commons.extensions.safeCall
import com.confinapptilus.confinpinboard.datasources.ResponseState
import com.confinapptilus.confinpinboard.datasources.mappers.AnnouncementMapper
import com.confinapptilus.confinpinboard.datasources.models.AnnouncementResponseModel
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel
import com.confinapptilus.confinpinboard.repositories.datasources.AnnouncementsDataSource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AnnouncementsDataSourceImpl(
    private val database: FirebaseFirestore,
    private val mapper: AnnouncementMapper
) : AnnouncementsDataSource {

    override suspend fun addAnnouncement(announcement: AnnouncementModel): ResponseState<String> =
        safeCall {
            @Suppress("RemoveExplicitTypeArguments")
            suspendCoroutine<String> { cont ->
                database.collection(COLLECTION_NAME)
                    .add(mapper.mapDomainToResponse(announcement))
                    .addOnSuccessListener { ref -> cont.resume(ref.id) }
                    .addOnFailureListener { error -> cont.resumeWithException(error) }
            }
        }

    override suspend fun getAnnouncements(
        ids: List<String>,
        searchTerm: String,
        categories: List<AnnouncementModel.Category>
    ): ResponseState<List<AnnouncementModel>> =
        safeCall {
            @Suppress("RemoveExplicitTypeArguments")
            suspendCoroutine<List<AnnouncementModel>> { cont ->
                database.collection(COLLECTION_NAME)
                    .get()
                    .addOnSuccessListener { documentsSnapshots ->
                        val announcements = getAnnouncementsFromDocuments(
                            documentsSnapshots,
                            ids,
                            searchTerm,
                            categories
                        )
                        cont.resume(announcements)
                    }
                    .addOnFailureListener { error -> cont.resumeWithException(error) }
            }
        }

    private fun getAnnouncementsFromDocuments(
        documentsSnapshots: QuerySnapshot,
        ids: List<String>,
        searchTerm: String,
        categories: List<AnnouncementModel.Category>
    ): List<AnnouncementModel> {
        var announcements = documentsSnapshots.toObjects(AnnouncementResponseModel::class.java)
            .filterNotNull()
            .filter {
                existsContent(it.title, it.description, it.place)
                        && checkAvailability(it.endTimestamp)
            }
            .sortedBy { it.startTimestamp }
            .map { mapper.mapResponseToDomain(it) }

        if (searchTerm.isNotEmpty()) {
            announcements = announcements.filter {
                it.title.contains(searchTerm, true)
                        || it.description.contains(searchTerm, true)
            }
        }

        if (ids.isNotEmpty()) {
            announcements = announcements.filter { ids.contains(it.id) }
        }

        if (categories.isNotEmpty()) {
            announcements = announcements.filter {
                containsFilterCategories(categories, it.categories)
            }
        }

        return announcements
    }

    private fun containsFilterCategories(
        filterCategories: List<AnnouncementModel.Category>,
        announcementCategories: List<AnnouncementModel.Category>
    ) : Boolean =
        announcementCategories.firstOrNull { filterCategories.contains(it) }?.let { true } ?: false

    private fun existsContent(title: String?, description: String?, place: String?): Boolean =
        title != null && title.isNotEmpty()
                && description != null && description.isNotEmpty()
                && place != null && place.isNotEmpty()

    private fun checkAvailability(endTimestamp: Long?): Boolean =
        endTimestamp != null && isAvailable(endTimestamp)


    companion object {
        private const val COLLECTION_NAME = "announcements"
    }
}

package com.coronapptilus.covidpinboard.datasources.impl

import com.coronapptilus.covidpinboard.commons.extensions.isAvailable
import com.coronapptilus.covidpinboard.commons.extensions.safeCall
import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.datasources.mappers.AnnouncementMapper
import com.coronapptilus.covidpinboard.datasources.models.AnnouncementResponseModel
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.repositories.datasources.AnnouncementsDataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AnnouncementsDataSourceImpl(
    private val database: FirebaseFirestore,
    private val mapper: AnnouncementMapper
) : AnnouncementsDataSource {

    override suspend fun addAnnouncement(announcement: AnnouncementModel): ResponseState<String> =
        safeCall {
            suspendCoroutine<String> { cont ->
                database.collection(COLLECTION_NAME)
                    .add(mapper.mapDomainToResponse(announcement))
                    .addOnSuccessListener { ref -> cont.resume(ref.id) }
                    .addOnFailureListener { error -> cont.resumeWithException(error) }
            }
        }

    override suspend fun getAnnouncementsByIds(ids: List<String>): ResponseState<List<AnnouncementModel>> =
        safeCall {
            suspendCoroutine<List<AnnouncementModel>> { cont ->
                database.collection(COLLECTION_NAME)
                    .whereIn(ID_KEY, ids)
                    .get()
                    .addOnSuccessListener { documentsSnapshots ->
                        val announcements =
                            documentsSnapshots.toObjects(AnnouncementResponseModel::class.java)
                                .filterNotNull()
                                .filter {
                                    existsContent(it.title, it.description, it.place)
                                            && checkAvailability(it.endTimestamp)
                                }
                                .map { mapper.mapResponseToDomain(it) }
                        cont.resume(announcements)
                    }
                    .addOnFailureListener { error -> cont.resumeWithException(error) }
            }
        }

    override suspend fun getAnnouncements(): ResponseState<List<AnnouncementModel>> =
        safeCall {
            suspendCoroutine<List<AnnouncementModel>> { cont ->
                database.collection(COLLECTION_NAME)
                    .get()
                    .addOnSuccessListener { documentsSnapshots ->
                        val announcements =
                            documentsSnapshots.toObjects(AnnouncementResponseModel::class.java)
                                .filterNotNull()
                                .filter {
                                    existsContent(it.title, it.description, it.place)
                                            && checkAvailability(it.endTimestamp)
                                }
                                .map { mapper.mapResponseToDomain(it) }
                        cont.resume(announcements)
                    }
                    .addOnFailureListener { error -> cont.resumeWithException(error) }
            }
        }

    private fun existsContent(title: String?, description: String?, place: String?): Boolean =
        title != null && title.isNotEmpty()
                && description != null && description.isNotEmpty()
                && place != null && place.isNotEmpty()

    private fun checkAvailability(endTimestamp: Long?): Boolean =
        endTimestamp != null && isAvailable(endTimestamp)


    companion object {
        private const val COLLECTION_NAME = "announcements"
        private const val ID_KEY = "id"
    }
}
package com.coronapptilus.covidpinboard.datasources.impl

import com.coronapptilus.covidpinboard.commons.extensions.isAvailable
import com.coronapptilus.covidpinboard.commons.extensions.safeCall
import com.coronapptilus.covidpinboard.datasources.ResponseState
import com.coronapptilus.covidpinboard.datasources.mappers.AnnouncementMapper
import com.coronapptilus.covidpinboard.datasources.models.AnnouncementResponseModel
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.repositories.datasources.AnnouncementsDataSource
import com.google.android.gms.tasks.Task
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
            suspendCoroutine<List<AnnouncementModel>> { cont ->
                val categoriesTypes = mapper.mapCategoriesToType(categories)
                getQueryTask(ids, categoriesTypes)
                    .addOnSuccessListener { documentsSnapshots ->
                        val announcements = getAnnouncementsFromDocuments(
                            documentsSnapshots,
                            ids,
                            searchTerm,
                            categoriesTypes
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
        categories: List<Int>
    ): List<AnnouncementModel> {
        var announcements = documentsSnapshots.toObjects(AnnouncementResponseModel::class.java)
            .filterNotNull()
            .filter {
                existsContent(it.title, it.description, it.place)
                        && checkAvailability(it.endTimestamp)
            }
            .map { mapper.mapResponseToDomain(it) }

        if (searchTerm.isNotEmpty()) {
            announcements = announcements.filter {
                it.title.contains(searchTerm, true)
                        || it.description.contains(searchTerm, true)
            }
        }

        if (ids.isNotEmpty() && categories.isNotEmpty()) {
            announcements = announcements.filter { ids.contains(it.id) }
        }

        return announcements
    }

    private fun getQueryTask(
        ids: List<String>,
        categoriesTypes: List<Int>
    ): Task<QuerySnapshot> {
        val collectionRef = database.collection(COLLECTION_NAME)

        return when {
            ids.isNotEmpty() && categoriesTypes.isEmpty() ->
                collectionRef.whereIn(ID_KEY, ids).get()
            ids.isNotEmpty() && categoriesTypes.isNotEmpty() ->
                collectionRef.whereArrayContainsAny(CATEGORIES_KEY, categoriesTypes)
                    .get()
            ids.isEmpty() && categoriesTypes.isNotEmpty() ->
                collectionRef.whereArrayContainsAny(CATEGORIES_KEY, categoriesTypes).get()
            else -> collectionRef.get()
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
        private const val CATEGORIES_KEY = "categoriesTypes"
    }
}
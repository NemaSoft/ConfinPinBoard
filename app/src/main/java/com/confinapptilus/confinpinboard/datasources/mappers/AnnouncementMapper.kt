package com.confinapptilus.confinpinboard.datasources.mappers

import com.confinapptilus.confinpinboard.commons.extensions.convertDateToTimestamp
import com.confinapptilus.confinpinboard.commons.extensions.convertTimestampToDate
import com.confinapptilus.confinpinboard.commons.extensions.convertTimestampToTime
import com.confinapptilus.confinpinboard.datasources.models.AnnouncementResponseModel
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel

class AnnouncementMapper {

    fun mapDomainToResponse(domainModel: AnnouncementModel): AnnouncementResponseModel =
        with(domainModel) {
            AnnouncementResponseModel(
                id = id,
                announcer = announcer,
                title = title,
                description = description,
                place = place,
                categoriesTypes = mapCategoriesToType(categories),
                targetType = mapTargetToType(target),
                startTimestamp = convertDateToTimestamp(startDate, startTime),
                endTimestamp = convertDateToTimestamp(endDate, endTime)
            )
        }

    fun mapResponseToDomain(responseModel: AnnouncementResponseModel?): AnnouncementModel =
        AnnouncementModel(
            id = responseModel?.id ?: "",
            announcer = responseModel?.announcer ?: "",
            title = responseModel?.title ?: "",
            description = responseModel?.description ?: "",
            place = responseModel?.place ?: "",
            categories = mapTypesToCategories(responseModel?.categoriesTypes),
            target = mapTypeToTarget(responseModel?.targetType),
            startDate = convertTimestampToDate(responseModel?.startTimestamp),
            startTime = convertTimestampToTime(responseModel?.startTimestamp),
            endDate = convertTimestampToDate(responseModel?.endTimestamp),
            endTime = convertTimestampToTime(responseModel?.endTimestamp)
        )

    private fun mapCategoriesToType(categories: List<AnnouncementModel.Category>): List<Int> =
        categories.map {
            when (it) {
                AnnouncementModel.Category.Sport -> AnnouncementModel.Category.Sport.type
                AnnouncementModel.Category.Cook -> AnnouncementModel.Category.Cook.type
                AnnouncementModel.Category.Music -> AnnouncementModel.Category.Music.type
                AnnouncementModel.Category.Dance -> AnnouncementModel.Category.Dance.type
                AnnouncementModel.Category.Theater -> AnnouncementModel.Category.Theater.type
                AnnouncementModel.Category.Literature -> AnnouncementModel.Category.Literature.type
                AnnouncementModel.Category.Cinema -> AnnouncementModel.Category.Cinema.type
                AnnouncementModel.Category.Conference -> AnnouncementModel.Category.Conference.type
                AnnouncementModel.Category.Interview -> AnnouncementModel.Category.Interview.type
                AnnouncementModel.Category.Workshop -> AnnouncementModel.Category.Workshop.type
                AnnouncementModel.Category.Formation -> AnnouncementModel.Category.Formation.type
                AnnouncementModel.Category.Donation -> AnnouncementModel.Category.Donation.type
                AnnouncementModel.Category.Crafts -> AnnouncementModel.Category.Crafts.type
                AnnouncementModel.Category.StoryTelling -> AnnouncementModel.Category.StoryTelling.type
                AnnouncementModel.Category.Others -> AnnouncementModel.Category.Others.type
            }
        }

    private fun mapTypesToCategories(types: List<Int?>?): List<AnnouncementModel.Category> =
        types?.map {
            when (it) {
                AnnouncementModel.Category.Sport.type -> AnnouncementModel.Category.Sport
                AnnouncementModel.Category.Cook.type -> AnnouncementModel.Category.Cook
                AnnouncementModel.Category.Music.type -> AnnouncementModel.Category.Music
                AnnouncementModel.Category.Dance.type -> AnnouncementModel.Category.Dance
                AnnouncementModel.Category.Theater.type -> AnnouncementModel.Category.Theater
                AnnouncementModel.Category.Literature.type -> AnnouncementModel.Category.Literature
                AnnouncementModel.Category.Cinema.type -> AnnouncementModel.Category.Cinema
                AnnouncementModel.Category.Conference.type -> AnnouncementModel.Category.Conference
                AnnouncementModel.Category.Interview.type -> AnnouncementModel.Category.Interview
                AnnouncementModel.Category.Workshop.type -> AnnouncementModel.Category.Workshop
                AnnouncementModel.Category.Formation.type -> AnnouncementModel.Category.Formation
                AnnouncementModel.Category.Donation.type -> AnnouncementModel.Category.Donation
                AnnouncementModel.Category.Crafts.type -> AnnouncementModel.Category.Crafts
                AnnouncementModel.Category.StoryTelling.type -> AnnouncementModel.Category.StoryTelling
                else -> AnnouncementModel.Category.Others
            }
        } ?: emptyList()

    private fun mapTargetToType(target: AnnouncementModel.Target): Int =
        when (target) {
            AnnouncementModel.Target.Adults -> AnnouncementModel.Target.Adults.type
            AnnouncementModel.Target.Children -> AnnouncementModel.Target.Children.type
            AnnouncementModel.Target.Familiar -> AnnouncementModel.Target.Familiar.type
            AnnouncementModel.Target.Undefined -> AnnouncementModel.Target.Undefined.type
        }

    private fun mapTypeToTarget(type: Int?): AnnouncementModel.Target =
        when (type) {
            AnnouncementModel.Target.Adults.type -> AnnouncementModel.Target.Adults
            AnnouncementModel.Target.Children.type -> AnnouncementModel.Target.Children
            AnnouncementModel.Target.Familiar.type -> AnnouncementModel.Target.Familiar
            else -> AnnouncementModel.Target.Undefined
        }
}

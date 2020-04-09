package com.coronapptilus.covidpinboard.datasources.mappers

import com.coronapptilus.covidpinboard.commons.extensions.convertDateToTimestamp
import com.coronapptilus.covidpinboard.commons.extensions.convertTimestampToDate
import com.coronapptilus.covidpinboard.commons.extensions.convertTimestampToTime
import com.coronapptilus.covidpinboard.datasources.models.AnnouncementResponseModel
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel

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
                AnnouncementModel.Category.WorkShop -> AnnouncementModel.Category.WorkShop.type
                AnnouncementModel.Category.Formation -> AnnouncementModel.Category.Formation.type
                AnnouncementModel.Category.Donation -> AnnouncementModel.Category.Donation.type
                AnnouncementModel.Category.Crafts -> AnnouncementModel.Category.Crafts.type
                AnnouncementModel.Category.StoryTeller -> AnnouncementModel.Category.StoryTeller.type
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
                AnnouncementModel.Category.WorkShop.type -> AnnouncementModel.Category.WorkShop
                AnnouncementModel.Category.Formation.type -> AnnouncementModel.Category.Formation
                AnnouncementModel.Category.Donation.type -> AnnouncementModel.Category.Donation
                AnnouncementModel.Category.Crafts.type -> AnnouncementModel.Category.Crafts
                AnnouncementModel.Category.StoryTeller.type -> AnnouncementModel.Category.StoryTeller
                else -> AnnouncementModel.Category.Others
            }
        } ?: listOf(AnnouncementModel.Category.Others)

    private fun mapTargetToType(target: AnnouncementModel.Target): Int =
        when (target) {
            AnnouncementModel.Target.Adults -> AnnouncementModel.Target.Adults.type
            AnnouncementModel.Target.Children -> AnnouncementModel.Target.Children.type
            AnnouncementModel.Target.Family -> AnnouncementModel.Target.Family.type
        }

    private fun mapTypeToTarget(type: Int?): AnnouncementModel.Target =
        when (type) {
            AnnouncementModel.Target.Adults.type -> AnnouncementModel.Target.Adults
            AnnouncementModel.Target.Children.type -> AnnouncementModel.Target.Children
            AnnouncementModel.Target.Family.type -> AnnouncementModel.Target.Family
            else -> AnnouncementModel.Target.Adults
        }
}
package com.coronapptilus.covidpinboard.repositories.datasources

// TODO: Add parameter and type after announcement model is defined
interface AnnouncementsDataSource {

    fun addAnnouncement()

    fun getAnnouncements()
}
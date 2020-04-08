package com.coronapptilus.covidpinboard.di

import com.coronapptilus.covidpinboard.datasources.api.AnnouncementsApi
import org.koin.dsl.module
import retrofit2.Retrofit

object ApiModule {

    val apiModule = module {
        factory { provideAnnouncementApi(get()) }
    }

    private fun provideAnnouncementApi(retrofit: Retrofit): AnnouncementsApi =
        retrofit.create(AnnouncementsApi::class.java)
}
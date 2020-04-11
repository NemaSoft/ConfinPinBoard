package com.coronapptilus.covidpinboard.di

import com.coronapptilus.covidpinboard.announcements.favorites.AnnouncementsFavoritesContract
import com.coronapptilus.covidpinboard.announcements.favorites.AnnouncementsFavoritesPresenter
import com.coronapptilus.covidpinboard.announcements.form.AnnouncementFormContract
import com.coronapptilus.covidpinboard.announcements.form.AnnouncementFormPresenter
import com.coronapptilus.covidpinboard.announcements.list.AnnouncementsListContract
import com.coronapptilus.covidpinboard.announcements.list.AnnouncementsListPresenter
import com.coronapptilus.covidpinboard.splash.SplashContract
import com.coronapptilus.covidpinboard.splash.SplashPresenter
import org.koin.dsl.module

object PresentersModule {

    val presentersModule = module {
        factory<SplashContract.Presenter> { SplashPresenter() }
        factory<AnnouncementFormContract.Presenter> { AnnouncementFormPresenter(get()) }
        factory<AnnouncementsListContract.Presenter> {
            AnnouncementsListPresenter(get(), get(), get(), get())
        }
        factory<AnnouncementsFavoritesContract.Presenter> {
            AnnouncementsFavoritesPresenter(get(), get(), get())
        }
    }
}

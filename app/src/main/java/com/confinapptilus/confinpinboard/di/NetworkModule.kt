package com.confinapptilus.confinpinboard.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import org.koin.dsl.module

object NetworkModule {

    val networkModule = module {
        factory { Gson() }
        factory { FirebaseFirestore.getInstance() }
    }
}

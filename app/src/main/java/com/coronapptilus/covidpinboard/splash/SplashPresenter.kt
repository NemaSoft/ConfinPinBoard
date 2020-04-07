package com.coronapptilus.covidpinboard.splash

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import okhttp3.internal.waitMillis

class SplashPresenter: SplashContract.Presenter {

    override var view: SplashContract.View? = null

    private val job = SupervisorJob()
    private val errorHandler = CoroutineExceptionHandler { _, _ ->  }
    private val coroutineScope = CoroutineScope(job + Main + errorHandler)

    override fun attachView(newView: SplashContract.View) {
        view = newView
    }

    override fun detachView() {
        view = null
        coroutineScope.cancel()
    }

    override fun applyDelay() {
        coroutineScope.launch {
            delay(2000L)
            view?.goToMain()
        }
    }
}
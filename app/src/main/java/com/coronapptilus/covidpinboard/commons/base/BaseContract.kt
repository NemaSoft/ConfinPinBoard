package com.coronapptilus.covidpinboard.commons.base

interface BaseContract {

    interface View

    interface Presenter<V : View> {

        var view: V?

        fun attachView(newView: V)

        fun detachView()
    }
}

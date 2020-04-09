package com.coronapptilus.covidpinboard.commons.extensions

import com.coronapptilus.covidpinboard.datasources.ResponseState

suspend fun <T> safeCall(call: suspend () -> T): ResponseState<T> =
    try {
        val response = call()
        ResponseState.Success(response)
    } catch (ex: Exception) {
        ResponseState.Failure(ex)
    }
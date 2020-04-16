package com.confinapptilus.confinpinboard.commons.extensions

import com.confinapptilus.confinpinboard.datasources.ResponseState

suspend fun <T> safeCall(call: suspend () -> T): ResponseState<T> =
    try {
        val response = call()
        ResponseState.Success(response)
    } catch (ex: Exception) {
        ResponseState.Failure(ex)
    }
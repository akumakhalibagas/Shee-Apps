package com.she.apps.data.source.remote.network

sealed class AppApiResponse<out R> {
    data class Success<out T>(val data: T) : AppApiResponse<T>()
    data class Error(val msg: String) : AppApiResponse<Nothing>()
}
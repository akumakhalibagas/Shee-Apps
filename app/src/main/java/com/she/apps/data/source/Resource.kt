package com.she.apps.data.source

sealed class Resource<out R> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val msg: String) : Resource<Nothing>()
}
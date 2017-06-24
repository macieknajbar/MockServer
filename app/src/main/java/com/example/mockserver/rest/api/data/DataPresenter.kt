package com.example.mockserver.rest.api.data

/**
 * Model communication layer to passing data returned from Data.
 * Data can be different like:
 * * Rest
 * * DB
 * * Local storage
 */
interface DataPresenter<T> {
    fun <T> onSuccess(data: T)
    fun onError()
}
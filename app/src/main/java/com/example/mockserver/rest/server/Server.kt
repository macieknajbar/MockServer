package com.example.mockserver.rest.server

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Wrapping class for our Retrofit's object. This class gives us more control with less effort over
 * 3rd party library.
 */
open class Server(val baseUrl: String, val client: OkHttpClient?) {
    protected var retrofit: Retrofit? = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()

    /**
     * This method returns initialized object of a speficied service's class.
     * Initialization takes place behind the scene.
     */
    fun <T> getService(serviceClass: Class<T>): T {
        return retrofit?.create(serviceClass)!!
    }
}
package com.example.mockserver

import android.app.Application
import com.example.mockserver.rest.ServerInjector
import com.example.mockserver.rest.server.Server
import okhttp3.OkHttpClient

/**
 * Project specific application class. All necessary configurations happen here.
 */
class MockServerApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        ServerInjector.setServer(Server("https://api.github.com/", OkHttpClient()))
    }
}
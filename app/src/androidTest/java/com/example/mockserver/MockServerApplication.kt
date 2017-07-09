package com.example.mockserver

import android.app.Application
import com.example.mockserver.rest.ServerInjector
import com.example.mockserver.rest.server.MockServer

/**
 * Project specific application class. All necessary configurations happen here.
 * Test version of the class.
 *
 * Especially created for purpose of creating Mock Server instance.
 */
class MockServerApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        ServerInjector.setServer(MockServer("http://sh.it"))
    }
}
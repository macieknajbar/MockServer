package com.example.mockserver

import android.app.Application
import com.example.mockserver.rest.ServerInjector
import com.example.mockserver.rest.server.MockServer
import java.io.InputStreamReader

/**
 * Project specific application class. All necessary configurations happen here.
 * Test version of the class.
 *
 * Especially created for purpose of creating Mock Server instance.
 */
class MockServerApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val server = MockServer("http://sh.it")
        val responseRepo = InputStreamReader(resources.openRawResource(R.raw.response_repos)).readText()
        server.rewriteResponse("users/octocat/repos", 200, responseRepo)

        ServerInjector.setServer(server)
    }
}
package com.example.mockserver.rest

import com.example.mockserver.rest.server.Server

/**
 * This is where mock server instance is being kept.
 */
class ServerInjector {
    companion object {
        private var mockServer: Server? = null

        fun setServer(server: Server) {
            mockServer = server
        }

        fun server(): Server {
            return mockServer!!
        }
    }
}
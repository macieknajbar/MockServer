package com.example.mockserver.rest.server

import com.example.mockserver.rest.api.GitHubService
import okhttp3.OkHttpClient
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ServerTest {

    var server: Server? = null

    private val BASE_URL: String = "https://api.github.com/"

    @Test fun `server can create API service`() {
        server = Server(BASE_URL, OkHttpClient())
        val gitHubService: GitHubService = server!!.getService(GitHubService::class.java)
        Assert.assertNotNull(gitHubService)
    }

    @Test fun `server requires OkHttpClient object`() {
        try {
            server = Server(BASE_URL, null)
        } catch (e: NullPointerException) {
        } finally {
            Assert.assertNull(server)
        }
    }
}
package com.example.mockserver.rest.server

import com.example.mockserver.rest.api.GitHubService
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ServerTest {

    var server: Server? = null

    private val BASE_URL: String = "https://api.github.com/"

    @Before fun setUp() {
        server = Server(BASE_URL)
    }

    @Test fun `server can create API service`() {
        val gitHubService: GitHubService = server!!.getService(GitHubService::class.java)
        Assert.assertNotNull(gitHubService)
    }
}
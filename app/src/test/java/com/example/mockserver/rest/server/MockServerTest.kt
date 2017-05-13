package com.example.mockserver.rest.server

import com.example.mockserver.rest.api.GitHubService
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MockServerTest {

    private val BASE_URL: String = "http://sh.it"

    private var server: MockServer? = null

    @Before fun setUp() {
        server = MockServer(BASE_URL)
    }

    @Test fun `returns successful response regardless anything`() {
        val response = server?.getService(GitHubService::class.java)?.listRepos("!!!")?.execute()
        Assert.assertEquals(200, response?.code())
    }
}
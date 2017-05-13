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

    @Test fun `can rewrite response`() {
        server?.rewriteResponse("users/!!!/repos", 404, "null")
        val response = server?.getService(GitHubService::class.java)?.listRepos("!!!")?.execute()
        Assert.assertEquals(404, response?.code())
    }

    @Test fun `overrides response for the same request`() {
        server?.rewriteResponse("users/!!!/repos", 404, "null")
        server?.rewriteResponse("users/!!!/repos", 500, "{message: \"internal error\"}")
        val response = server?.getService(GitHubService::class.java)?.listRepos("!!!")?.execute()
        Assert.assertEquals(500, response?.code())
    }
}
package com.example.mockserver.rest.server

import com.example.mockserver.rest.api.GitHubService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.http.GET

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
        server?.rewriteResponse("users/!!!/repos", 500, "{\"message\": \"internal error\"}")
        val response = server?.getService(GitHubService::class.java)?.listRepos("!!!")?.execute()
        Assert.assertEquals(500, response?.code())
    }

    @Test fun `can rewrite responses for multiple requests and parse responses to objects`() {
        server?.rewriteResponse("something/", 404, "null")
        server?.rewriteResponse("users/!!!/repos", 200, reposFakeResponse)

        val something = server?.getService(SomeAPI::class.java)?.something()?.execute()!!
        val repos = server?.getService(GitHubService::class.java)?.listRepos("!!!")?.execute()!!

        Assert.assertEquals(404, something.code())
        Assert.assertEquals(200, repos.code())
        Assert.assertEquals(2, repos.body().size)
        Assert.assertEquals(2, repos.body()[1].id)
        Assert.assertEquals("Mock Server", repos.body()[1].name)
        Assert.assertEquals(302, repos.body()[1].stargazers_count)
    }

    interface SomeAPI {
        @GET("something/")
        fun something(): Call<Any>
    }

    var reposFakeResponse = "" +
            "[" +
            "  {" +
            "    \"id\": 1," +
            "    \"name\": \"Mock#1\"," +
            "    \"stargazers_count\": 3" +
            "  }," +
            "  {" +
            "    \"id\": 2," +
            "    \"name\": \"Mock Server\"," +
            "    \"stargazers_count\": 302" +
            "  }" +
            "]"
}
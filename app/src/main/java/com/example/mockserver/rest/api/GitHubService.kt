package com.example.mockserver.rest.api

import com.example.mockserver.rest.dto.RepoDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Server communication requests interface to GitHub's user's information.
 *
 * © This example is from Retrofit's official website adapted for Kotlin.
 */
interface GitHubService {

    /**
     * Request user's repositories.
     * @param   user Username in GitHub
     * @return List of specified users repositories.
     */
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<RepoDTO>>
}
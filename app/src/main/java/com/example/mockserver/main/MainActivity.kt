package com.example.mockserver.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ListView
import com.example.mockserver.R
import com.example.mockserver.rest.ServerInjector
import com.example.mockserver.rest.api.GitHubService
import com.example.mockserver.rest.dto.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),
        Callback<List<Repo>> {

    private val server = ServerInjector.server()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gitHubService = server.getService(GitHubService::class.java)
        gitHubService.listRepos("octocat").enqueue(this)

    }

    override fun onResponse(call: Call<List<Repo>>?, response: Response<List<Repo>>?) {
        findViewById(R.id.progress_bar).visibility = View.GONE
        val projectList = findViewById(R.id.project_list) as ListView

        for (repo in response!!.body()) {
            Log.d("MainActivity", "id: " + repo.id + ", name: " + repo.name + ", stars: " + repo.stargazers_count)
        }
    }

    override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {
        Log.e("MainActivity", "Something went wrong!", t)
    }
}

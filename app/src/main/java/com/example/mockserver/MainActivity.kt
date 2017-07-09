package com.example.mockserver

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.mockserver.controllers.ProjectListAdapter
import com.example.mockserver.rest.ServerInjector
import com.example.mockserver.rest.api.GitHubService
import com.example.mockserver.rest.dto.RepoDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),
        Callback<List<RepoDTO>> {

    private lateinit var projectListView: RecyclerView

    private val server = ServerInjector.server()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        projectListView = findViewById(R.id.project_list) as RecyclerView
        projectListView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val gitHubService = server.getService(GitHubService::class.java)
        gitHubService.listRepos("octocat").enqueue(this)
    }

    override fun onResponse(call: Call<List<RepoDTO>>?, response: Response<List<RepoDTO>>?) {
        findViewById(R.id.progress_bar).visibility = View.GONE

        if (response!!.isSuccessful) {
            projectListView.adapter = ProjectListAdapter(response.body())
        } else {
            onFailure(call, null)
        }
    }

    override fun onFailure(call: Call<List<RepoDTO>>?, t: Throwable?) {
        val infoBox = findViewById(R.id.info_box) as TextView
        infoBox.visibility = View.VISIBLE
        infoBox.text = getString(R.string.general_error_text)
        infoBox.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
    }
}

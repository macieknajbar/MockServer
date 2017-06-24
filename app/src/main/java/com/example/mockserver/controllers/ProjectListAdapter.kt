package com.example.mockserver.controllers

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mockserver.R
import com.example.mockserver.rest.dto.RepoDTO

/**
 * Responsible for creating and displaying the row views for GitHub projects.
 */
class ProjectListAdapter(private val projectList: List<RepoDTO>): RecyclerView.Adapter<ProjectsListRowViewHolder>() {

    private lateinit var context: Context

    override fun onBindViewHolder(holder: ProjectsListRowViewHolder?, position: Int) {
        holder!!.id.text = projectList[position].id.toString()
        holder.name.text = projectList[position].name
        holder.stars.text = projectList[position].stargazers_count.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProjectsListRowViewHolder {
        context = parent!!.context
        val view = LayoutInflater.from(context).inflate(R.layout.row_repo, parent, false)
        return ProjectsListRowViewHolder(view)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }
}

class ProjectsListRowViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val id: TextView = view.findViewById(R.id.row_repo_id) as TextView
    val name: TextView = view.findViewById(R.id.row_repo_name) as TextView
    val stars: TextView = view.findViewById(R.id.row_repo_stars) as TextView
}
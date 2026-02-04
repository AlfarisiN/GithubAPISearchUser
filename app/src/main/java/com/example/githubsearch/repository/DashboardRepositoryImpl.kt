package com.example.githubsearch.repository

import android.R
import com.example.githubsearch.model.DashboardModel

class DashboardRepositoryImpl @javax.inject.Inject constructor() : DashboardRepository {
    override fun getDashboardItems(): List<DashboardModel> {
        return listOf(
            DashboardModel(1, "Search User", R.drawable.ic_menu_search)
        )
    }
}
package com.example.githubsearch.repository

import android.R
import com.example.githubsearch.model.DashboardModel

interface DashboardRepository {
    fun getDashboardItems(): List<DashboardModel>
}
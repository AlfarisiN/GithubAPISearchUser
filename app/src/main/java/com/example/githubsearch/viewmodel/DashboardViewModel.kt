package com.example.githubsearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubsearch.model.DashboardModel
import com.example.githubsearch.repository.DashboardRepository
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<DashboardModel>>()
    val item: LiveData<List<DashboardModel>> = _items

    fun loadDashboard() {
        _items.value = repository.getDashboardItems()
    }

}
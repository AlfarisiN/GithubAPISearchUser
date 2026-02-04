package com.example.githubsearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val dashboardVM: Provider<DashboardViewModel>,
    private val searchUserVM: Provider<SearchUserViewModel>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return create(modelClass)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return dashboardVM.get() as T
        }
        if (modelClass.isAssignableFrom(SearchUserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return searchUserVM.get() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class $modelClass")
    }
}
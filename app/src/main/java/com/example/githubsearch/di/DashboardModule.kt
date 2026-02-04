package com.example.githubsearch.di

import androidx.lifecycle.ViewModelProvider
import com.example.githubsearch.repository.DashboardRepository
import com.example.githubsearch.repository.DashboardRepositoryImpl
import com.example.githubsearch.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface DashboardModule {

    @Binds
    fun bindRepository(
        impl: DashboardRepositoryImpl
    ): DashboardRepository

    @Binds
    fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}
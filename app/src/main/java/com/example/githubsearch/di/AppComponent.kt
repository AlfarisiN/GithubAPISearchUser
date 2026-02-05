package com.example.githubsearch.di

import android.app.Application
import com.example.githubsearch.view.DashboardActivity
import com.example.githubsearch.view.SearchActivity
import com.example.githubsearch.view.UserProfileActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DashboardModule::class,
    NetworkModule::class,
    DatabaseModule::class
])
interface AppComponent {
    fun inject(activity: DashboardActivity)
    fun inject(activity: SearchActivity)
    fun inject(activity: UserProfileActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
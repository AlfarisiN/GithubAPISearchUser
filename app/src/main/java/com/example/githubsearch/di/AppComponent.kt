package com.example.githubsearch.di

import android.app.Application
import android.content.Context
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

    @Component.Factory
    interface Factory {
//        fun create(@BindsInstance context: Context): AppComponent
        fun create(@BindsInstance application: Application): AppComponent
    }
    fun inject(activity: DashboardActivity)
    fun inject(activity: SearchActivity)
    fun inject(activity: UserProfileActivity)

//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        fun application(application: Application): Builder
//
//        fun build(): AppComponent
//    }
}
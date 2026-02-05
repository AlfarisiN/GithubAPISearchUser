package com.example.githubsearch

import android.app.Application
import com.example.githubsearch.di.AppComponent
import com.example.githubsearch.di.DaggerAppComponent

class MyApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)

//        appComponent = DaggerAppComponent.builder()
//            .application(this)
//            .build()
    }
}
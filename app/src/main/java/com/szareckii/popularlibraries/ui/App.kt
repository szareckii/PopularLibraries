package com.szareckii.popularlibraries.ui

import android.app.Application
import com.szareckii.popularlibraries.di.AppComponent
import com.szareckii.popularlibraries.di.DaggerAppComponent
import com.szareckii.popularlibraries.di.modules.AppModule
import com.szareckii.popularlibraries.di.repository.RepositorySubcomponent
import com.szareckii.popularlibraries.di.user.UserSubcomponent
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database

class App: Application() {
    companion object{
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var userSubcomponent: UserSubcomponent? = null
        private set

    var repositorySubcomponent: RepositorySubcomponent? = null
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }

    fun releaseUserSubcomponent() {
        userSubcomponent = null
    }

    fun initRepositorySubcomponent() = userSubcomponent?.repositorySubcomponent().also {
          repositorySubcomponent = it
    }

    fun releaseRepositorySubcomponent() {
        repositorySubcomponent = null
    }
}
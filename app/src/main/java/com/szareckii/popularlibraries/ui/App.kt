package com.szareckii.popularlibraries.ui

import android.app.Application
import com.szareckii.popularlibraries.di.AppComponent
import com.szareckii.popularlibraries.di.DaggerAppComponent
import com.szareckii.popularlibraries.di.modules.AppModule
import com.szareckii.popularlibraries.di.movie.MovieSubcomponent
import com.szareckii.popularlibraries.di.repository.ActorSubcomponent

class App: Application() {
    companion object{
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var movieSubcomponent: MovieSubcomponent? = null
        private set

    var repositorySubcomponent: ActorSubcomponent? = null
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initMovieSubcomponent() = appComponent.movieSubcomponent().also {
        movieSubcomponent = it
    }

    fun releaseMovieSubcomponent() {
        movieSubcomponent = null
    }

    fun initRepositorySubcomponent() = movieSubcomponent?.repositorySubcomponent().also {
          repositorySubcomponent = it
    }

    fun releaseRepositorySubcomponent() {
        repositorySubcomponent = null
    }
}
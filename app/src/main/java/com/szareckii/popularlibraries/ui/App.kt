package com.szareckii.popularlibraries.ui

import android.app.Application
import com.szareckii.popularlibraries.di.AppComponent
import com.szareckii.popularlibraries.di.DaggerAppComponent
import com.szareckii.popularlibraries.di.modules.AppModule
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database

class App: Application() {
    companion object{
        lateinit var instance: App
        val component get() = instance._appComponent
    }

    private lateinit var _appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        _appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }


}
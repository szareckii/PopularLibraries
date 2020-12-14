package com.szareckii.popularlibraries.di.modules

import com.szareckii.popularlibraries.ui.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import java.io.File

@Module
class AppModule(val app: App) {

    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    fun cacheDir(): File = App.instance.cacheDir

    @Provides
    fun app(): App {
        return app
    }
}
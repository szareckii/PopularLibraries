package com.szareckii.popularlibraries.di.modules

import com.szareckii.popularlibraries.mvp.model.image.IImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun image(): IImageLoader =
//    GlideImageLoader(RoomImageCache(database, App.instance.cacheDir)
}
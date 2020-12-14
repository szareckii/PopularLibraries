package com.szareckii.popularlibraries.di.modules

import androidx.room.Room
import com.szareckii.popularlibraries.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import com.szareckii.popularlibraries.mvp.model.repo.cache.IGithubUsersCache
import com.szareckii.popularlibraries.mvp.model.cache.room.RoomGithubUsersCache
import com.szareckii.popularlibraries.mvp.model.repo.cache.IGithubRepositoriesCache
import com.szareckii.popularlibraries.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IGithubUsersCache = RoomGithubUsersCache(database)

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache = RoomGithubRepositoriesCache(database)
}
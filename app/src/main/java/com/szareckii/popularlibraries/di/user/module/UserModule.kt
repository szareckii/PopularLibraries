package com.szareckii.popularlibraries.di.user.module

import com.szareckii.popularlibraries.di.UserScope
import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.cache.room.RoomGithubUsersCache
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus
import com.szareckii.popularlibraries.mvp.model.repo.IGithubUsersRepo
import com.szareckii.popularlibraries.mvp.model.repo.cache.IGithubUsersCache
import com.szareckii.popularlibraries.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @UserScope
    @Provides
    fun usersCache(database: Database): IGithubUsersCache = RoomGithubUsersCache(database)


    @UserScope
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache) : IGithubUsersRepo =
            RetrofitGithubUsersRepo(api, networkStatus, cache)

}
package com.szareckii.popularlibraries.di.repository.module

import com.szareckii.popularlibraries.di.RepositoryScope
import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus
import com.szareckii.popularlibraries.mvp.model.repo.IGithubRepositoriesRepo
import com.szareckii.popularlibraries.mvp.model.repo.cache.IGithubRepositoriesCache
import com.szareckii.popularlibraries.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @RepositoryScope
    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache = RoomGithubRepositoriesCache(database)


    @RepositoryScope
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubRepositoriesCache) : IGithubRepositoriesRepo =
            RetrofitGithubRepositoriesRepo(api, networkStatus, cache)

}
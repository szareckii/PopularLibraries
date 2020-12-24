package com.szareckii.popularlibraries.di.repository.module

import com.szareckii.popularlibraries.di.RepositoryScope
import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.cache.IMovieActorsCache
import com.szareckii.popularlibraries.mvp.model.cache.room.RoomActorsCache
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus
import com.szareckii.popularlibraries.mvp.model.repo.IActorsRepo
import com.szareckii.popularlibraries.mvp.model.repo.retrofit.RetrofitActorsRepo
import dagger.Module
import dagger.Provides

@Module
class ActorModule {

//    @RepositoryScope
//    @Provides
//    fun repositoriesCache(database: Database): IGithubRepositoriesCache = RoomGithubRepositoriesCache(database)

//    @RepositoryScope
//    @Provides
//    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubRepositoriesCache) : IGithubRepositoriesRepo =
//            RetrofitGithubRepositoriesRepo(api, networkStatus, cache)

    @RepositoryScope
    @Provides
    fun actorsCache(database: Database): IMovieActorsCache = RoomActorsCache(database)

    @RepositoryScope
    @Provides
    fun actorsRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IMovieActorsCache) : IActorsRepo =
            RetrofitActorsRepo(api, networkStatus, cache)

}
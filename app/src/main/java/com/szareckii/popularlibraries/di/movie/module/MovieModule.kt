package com.szareckii.popularlibraries.di.movie.module

import com.szareckii.popularlibraries.di.UserScope
import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.cache.IMoviesCache
import com.szareckii.popularlibraries.mvp.model.cache.room.RoomMoviesCache
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus
import com.szareckii.popularlibraries.mvp.model.repo.IMoviesRepo
import com.szareckii.popularlibraries.mvp.model.repo.retrofit.RetrofitMoviesRepo
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @UserScope
    @Provides
    fun moviesCache(database: Database): IMoviesCache = RoomMoviesCache(database)


    @UserScope
    @Provides
    fun moviesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IMoviesCache) : IMoviesRepo =
            RetrofitMoviesRepo(api, networkStatus, cache)

}
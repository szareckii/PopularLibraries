package com.szareckii.popularlibraries.di.movie.module

import com.szareckii.popularlibraries.di.UserScope
import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.cache.IIMDBMoviesCache
import com.szareckii.popularlibraries.mvp.model.cache.room.RoomIMDBMoviesCache
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus
import com.szareckii.popularlibraries.mvp.model.repo.IIMDBMoviesRepo
import com.szareckii.popularlibraries.mvp.model.repo.retrofit.RetrofitIMDBMoviesRepo
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @UserScope
    @Provides
    fun moviesCache(database: Database): IIMDBMoviesCache = RoomIMDBMoviesCache(database)


    @UserScope
    @Provides
    fun moviesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IIMDBMoviesCache) : IIMDBMoviesRepo =
            RetrofitIMDBMoviesRepo(api, networkStatus, cache)

}
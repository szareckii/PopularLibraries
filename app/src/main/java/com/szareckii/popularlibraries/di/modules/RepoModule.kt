package com.szareckii.popularlibraries.di.modules

import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus
import com.szareckii.popularlibraries.mvp.model.repo.IGithubRepositoriesRepo
import com.szareckii.popularlibraries.mvp.model.repo.IGithubUsersRepo
import com.szareckii.popularlibraries.mvp.model.repo.cache.IGithubRepositoriesCache
import com.szareckii.popularlibraries.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.szareckii.popularlibraries.mvp.model.repo.cache.IGithubUsersCache
import com.szareckii.popularlibraries.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache) : IGithubUsersRepo =
        RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubRepositoriesCache) : IGithubRepositoriesRepo =
        RetrofitGithubRepositoriesRepo(api, networkStatus, cache)

}
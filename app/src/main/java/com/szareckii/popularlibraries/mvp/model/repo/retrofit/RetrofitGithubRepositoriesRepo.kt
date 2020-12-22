package com.szareckii.popularlibraries.mvp.model.repo.retrofit

import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.entity.GithubRepository
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus
import com.szareckii.popularlibraries.mvp.model.repo.IGithubRepositoriesRepo
import com.szareckii.popularlibraries.mvp.model.repo.cache.IGithubRepositoriesCache
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException

class RetrofitGithubRepositoriesRepo(private val api: IDataSource, private val networkStatus: INetworkStatus, private val cacheRepositories: IGithubRepositoriesCache):
    IGithubRepositoriesRepo {

    override fun getRepositories(user: Movie): Single<List<GithubRepository>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
//            user.reposUrl?.let { url ->
            user.title?.let { url ->
                api.getRepositories(url).flatMap { repositories ->
                    cacheRepositories.putUserRepos(user, repositories).andThen(Single.just(repositories))
                }
            } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url")).subscribeOn(Schedulers.io())

        } else {
            cacheRepositories.getUserRepos(user)
        }
    }.subscribeOn(Schedulers.io())

}

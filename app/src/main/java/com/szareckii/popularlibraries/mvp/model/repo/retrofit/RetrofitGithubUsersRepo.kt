package com.szareckii.popularlibraries.mvp.model.repo.retrofit

import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus
import com.szareckii.popularlibraries.mvp.model.repo.IGithubUsersRepo
import com.szareckii.popularlibraries.mvp.model.repo.cache.IGithubUsersCache
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(private val api: IDataSource, private val networkStatus: INetworkStatus, private val cacheUsers: IGithubUsersCache):
    IGithubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                cacheUsers.putUsers(users).andThen(Single.just(users))
            }
        } else {
            cacheUsers.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}

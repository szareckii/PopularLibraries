package com.szareckii.popularlibraries.mvp.model.repo

import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.entity.GithubRepository
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException

class RetrofitGithubRepositoriesRepo(private val api: IDataSource): IGithubRepositoriesRepo {

    override fun getRepositories(user: GithubUser): Single<List<GithubRepository>> = user.reposUrl?.let { url ->
        api.getRepositories(url).subscribeOn(Schedulers.io())
    } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url")).subscribeOn(Schedulers.io())
}

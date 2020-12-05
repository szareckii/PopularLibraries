package com.szareckii.popularlibraries.mvp.model.repo

import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.entity.GithubReposFork
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.model.entity.GithubRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(private val api: IDataSource): IGithubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> = api.getUsers().subscribeOn(Schedulers.io())

}

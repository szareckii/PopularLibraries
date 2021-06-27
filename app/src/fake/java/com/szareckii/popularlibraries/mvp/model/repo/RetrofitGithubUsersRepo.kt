package com.szareckii.popularlibraries.mvp.model.repo

import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.entity.GithubReposFork
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.model.entity.GithubRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(private val api: IDataSource): IGithubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> = getFakeResponse()

    private fun getFakeResponse(): Single<List<GithubUser>> {
        val list: MutableList<GithubUser> = mutableListOf()
        for (index in 1..100) {
            list.add(
                GithubUser(
                    id = index.toString(),
                    login = "Login: $index",
                    avatarUrl = "AvatarUrl: $index",
                    reposUrl = "ReposUrl: $index"
                )
            )
        }
        return Single.just(list)
    }
}



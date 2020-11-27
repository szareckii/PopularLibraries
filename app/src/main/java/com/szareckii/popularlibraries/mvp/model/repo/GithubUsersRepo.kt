package com.szareckii.popularlibraries.mvp.model.repo

import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo {

    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
        GithubUser("login6")
    )

    fun getUsers() =  Single.create<List<GithubUser>> { emitter ->
        emitter.onSuccess(repositories)
    }.subscribeOn(Schedulers.io())

}
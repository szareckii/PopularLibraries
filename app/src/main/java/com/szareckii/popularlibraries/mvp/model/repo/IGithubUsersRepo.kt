package com.szareckii.popularlibraries.mvp.model.repo

import com.szareckii.popularlibraries.mvp.model.entity.GithubReposFork
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.model.entity.GithubUserRepository
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {

    fun getUsers(): Single<List<GithubUser>>
    fun getUserRepos(url: String): Single<List<GithubUserRepository>>
    fun getUserForks(url: String): Single<List<GithubReposFork>>
}
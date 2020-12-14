package com.szareckii.popularlibraries.mvp.model.repo.cache

import com.szareckii.popularlibraries.mvp.model.entity.GithubRepository
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {
    fun putUserRepos(user: GithubUser, repositories: List<GithubRepository>): Completable
    fun getUserRepos(user: GithubUser): Single<List<GithubRepository>>
}
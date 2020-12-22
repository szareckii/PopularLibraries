package com.szareckii.popularlibraries.mvp.model.repo

import com.szareckii.popularlibraries.mvp.model.entity.GithubRepository
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesRepo {

    fun getRepositories(user: Movie): Single<List<GithubRepository>>
}
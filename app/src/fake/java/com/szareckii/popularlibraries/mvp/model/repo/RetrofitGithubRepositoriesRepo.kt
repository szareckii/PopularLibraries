package com.szareckii.popularlibraries.mvp.model.repo

import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.entity.GithubRepository
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException

class RetrofitGithubRepositoriesRepo(private val api: IDataSource): IGithubRepositoriesRepo {

    override fun getRepositories(user: GithubUser): Single<List<GithubRepository>> = getFakeResponse()

    private fun getFakeResponse(): Single<List<GithubRepository>> {
        val list: MutableList<GithubRepository> = mutableListOf()
        for (index in 1..100) {
            list.add(
                GithubRepository(
                    id = index.toString(),
                    name = "Name: $index",
                    forksCount = "forksCount: $index"
                )
            )
        }
        return Single.just(list)
    }
}

package com.szareckii.popularlibraries.mvp.model.cache.room

import com.szareckii.popularlibraries.mvp.model.entity.GithubRepository
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.model.entity.room.RoomGithubRepository
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import com.szareckii.popularlibraries.mvp.model.repo.cache.IGithubRepositoriesCache
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubRepositoriesCache(val db: Database): IGithubRepositoriesCache {

    override fun putUserRepos(user: GithubUser, repositories: List<GithubRepository>): Completable = Completable.fromAction {
        val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw java.lang.RuntimeException("No such users is database")
        val roomRepos = repositories.map {
            RoomGithubRepository(
                    it.id ?: "",
                    it.name ?: "",
                    it.forksCount ?: "",
                    it.fullName ?: "",
                    roomUser.id
            )
        }
        db.repositoryDao.insert(roomRepos)
    }.subscribeOn(Schedulers.io())

    override fun getUserRepos(user: GithubUser): Single<List<GithubRepository>> = Single.fromCallable {
        val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such users is database")
        return@fromCallable  db.repositoryDao.findForUser(roomUser.id).map { GithubRepository(it.id, it.name, it.forksCount, it.fullName) }
        }.subscribeOn(Schedulers.io())
}
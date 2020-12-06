package com.szareckii.popularlibraries.mvp.model.repo.cache

import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.model.entity.room.RoomGithubUser
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubUsersCache(val db: Database): IRoomGithubUsersCache {
    private fun putUsersInDB(users: List<GithubUser>): Boolean {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                user.id ?: "",
                user.login ?: "",
                user.avatarUrl ?: "",
                user.reposUrl ?: ""
            )
        }
        db.userDao.insert(roomUsers)
        return true
    }

    override fun putUsers(users: List<GithubUser>): Completable = Completable.create { emitter ->

        putUsersInDB(users).let {
            if (it) {
                emitter.onComplete()
            } else {
                emitter.onError(RuntimeException("Error"))
                return@create
            }
        }
    }.subscribeOn(Schedulers.io())

    override fun getUsers(): Single<List<GithubUser>> =
        Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
            }
        }.subscribeOn(Schedulers.io())
}
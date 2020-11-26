package com.szareckii.popularlibraries.mvp.model.repo

import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {

    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser(""),
        GithubUser("login5"),
        GithubUser("login6")
    )

//    Пример создания простого Observable
//    fun getUsers() =  Observable.fromIterable(repositories)
//            .doOnNext { println("add user ${it.login}") }

//    Создание Observable у которого пустой логин заменяется на заданную строку
    fun getUsersNotNull() = Observable.create<GithubUser> { emitter ->
            try {
                repositories.forEach { user ->
                    if (user is GithubUser) {
                        if (user.login == "") {
                            emitter.onNext(GithubUser("Empty_login"))
                        } else {
                            emitter.onNext(user)
                        }
                    } else {
                        emitter.onError(RuntimeException("Error"))
                        return@create
                    }
                }
                emitter.onComplete()
            } catch (t: Throwable) {
                emitter.onError(RuntimeException("Error"))
            }
        }.doOnNext { println("add user ${it.login}") }

}
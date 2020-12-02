package com.szareckii.popularlibraries.mvp.model.api

import com.szareckii.popularlibraries.mvp.model.entity.GithubUserRepository
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Url

interface IDataSource {

    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET("users/{login}")
    fun getUser(@Part("login") login: String): Single<GithubUser>

    @GET
    fun getUserRepos(@Url url: String): Single<List<GithubUserRepository>>
}
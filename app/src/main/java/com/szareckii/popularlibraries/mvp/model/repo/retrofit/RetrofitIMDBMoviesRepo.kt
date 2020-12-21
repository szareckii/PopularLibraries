package com.szareckii.popularlibraries.mvp.model.repo.retrofit

import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.cache.IIMDBMoviesCache
import com.szareckii.popularlibraries.mvp.model.entity.IMDBMovie
import com.szareckii.popularlibraries.mvp.model.entity.MoviesList
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus
import com.szareckii.popularlibraries.mvp.model.repo.IIMDBMoviesRepo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitIMDBMoviesRepo(private val api: IDataSource, private val networkStatus: INetworkStatus, private val cacheUsers: IIMDBMoviesCache):
        IIMDBMoviesRepo {

    override fun getMoviesList(): Single<List<IMDBMovie>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
                api.getMoviesList().flatMap { moviesList ->
                    Observable.fromArray(MoviesList)
                }
//            api.getMoviesList().flatMap { moviesList ->
//                cacheUsers.putMovies(moviesList).andThen(Single.just(moviesList))
        }
        else {
            cacheUsers.getMovies()
        }
    }.subscribeOn(Schedulers.io())

//    override fun getMovies(): Single<List<IMDBMovie>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
//        if (isOnline) {
//            api.getUsers().flatMap { users ->
//                cacheUsers.putMovies(users).andThen(Single.just(users))
//            }
//        } else {
//            cacheUsers.getMovies()
//        }
//    }.subscribeOn(Schedulers.io())
}


//RetrofitGithubUsersRepo
//override fun getMoviesList(): Single<MoviesList> = api.getMoviesList().subscribeOn(Schedulers.io())
